package com.geecoders.retrofit_compose.ui.coin_list.data.models

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd: Double,
    val dateTime: ZonedDateTime
)