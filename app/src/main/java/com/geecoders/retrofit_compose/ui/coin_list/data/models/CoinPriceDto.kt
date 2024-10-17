package com.geecoders.retrofit_compose.ui.coin_list.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDto(
    val priceUsd: Double,
    val time: Long
)