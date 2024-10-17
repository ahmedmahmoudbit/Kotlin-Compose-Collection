package com.geecoders.retrofit_compose.ui.coin_list.data.models

import androidx.compose.runtime.Immutable
import com.geecoders.retrofit_compose.ui.coin_list.data.datasource.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null
)