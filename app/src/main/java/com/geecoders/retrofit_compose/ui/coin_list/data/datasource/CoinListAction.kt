package com.geecoders.retrofit_compose.ui.coin_list.data.datasource

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
}