package com.geecoders.retrofit_compose.ui.coin_list.data.datasource

import android.os.Build
import androidx.annotation.RequiresApi
import com.geecoders.retrofit_compose.core.utils.constructUrl
import com.geecoders.retrofit_compose.core.network.NetworkError
import com.geecoders.retrofit_compose.core.network.ResultResponse
import com.geecoders.retrofit_compose.core.network.map
import com.geecoders.retrofit_compose.core.network.safeCall
import com.geecoders.retrofit_compose.ui.Coin
import com.geecoders.retrofit_compose.ui.coin_list.data.models.CoinHistoryDto
import com.geecoders.retrofit_compose.ui.coin_list.data.models.CoinPrice
import com.geecoders.retrofit_compose.ui.coin_list.data.models.CoinsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSourceImp(
    private val httpClient: HttpClient
): CoinDataSourceBase {

    override suspend fun getCoins(): ResultResponse<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): ResultResponse<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}