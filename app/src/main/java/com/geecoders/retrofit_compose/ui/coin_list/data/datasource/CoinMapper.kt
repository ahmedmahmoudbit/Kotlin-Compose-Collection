package com.geecoders.retrofit_compose.ui.coin_list.data.datasource

import android.os.Build
import androidx.annotation.RequiresApi
import com.geecoders.retrofit_compose.ui.Coin
import com.geecoders.retrofit_compose.ui.coin_list.data.models.CoinDto
import com.geecoders.retrofit_compose.ui.coin_list.data.models.CoinPrice
import com.geecoders.retrofit_compose.ui.coin_list.data.models.CoinPriceDto
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.systemDefault())
    )
}