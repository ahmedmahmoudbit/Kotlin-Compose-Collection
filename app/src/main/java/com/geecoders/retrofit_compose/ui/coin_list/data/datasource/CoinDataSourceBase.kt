package com.geecoders.retrofit_compose.ui.coin_list.data.datasource

import com.geecoders.retrofit_compose.core.network.NetworkError
import com.geecoders.retrofit_compose.core.network.ResultResponse
import com.geecoders.retrofit_compose.ui.Coin
import com.geecoders.retrofit_compose.ui.coin_list.data.models.CoinPrice
import java.time.ZonedDateTime

interface CoinDataSourceBase {
    suspend fun getCoins(): ResultResponse<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): ResultResponse<List<CoinPrice>, NetworkError>
}