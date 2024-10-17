package com.geecoders.retrofit_compose.ui.coin_list.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    val data: List<CoinDto>
)