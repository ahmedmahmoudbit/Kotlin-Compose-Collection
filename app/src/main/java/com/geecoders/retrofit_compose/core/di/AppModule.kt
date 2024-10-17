package com.geecoders.retrofit_compose.core.di

import com.geecoders.retrofit_compose.core.network.HttpClientFactory
import com.geecoders.retrofit_compose.ui.coin_list.data.datasource.CoinDataSourceBase
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.geecoders.retrofit_compose.ui.CoinListViewModel
import com.geecoders.retrofit_compose.ui.coin_list.data.datasource.RemoteCoinDataSourceImp



val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSourceImp).bind<CoinDataSourceBase>()
    viewModelOf(::CoinListViewModel)
}