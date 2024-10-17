package com.geecoders.retrofit_compose.core.network

import android.content.Context
import com.geecoders.retrofit_compose.R

interface Error

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN,
}

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}

fun NetworkError.toString(context: Context): String {
    val resId = when(this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERVER_ERROR -> R.string.error_unknown
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
    }
    return context.getString(resId)
}

typealias DomainError = Error

sealed interface ResultResponse<out D, out E: Error> {
    data class Success<out D>(val data: D): ResultResponse<D, Nothing>
    data class Error<out E: DomainError>(val error: E): ResultResponse<Nothing, E>
}

inline fun <T, E: Error, R> ResultResponse<T, E>.map(map: (T) -> R): ResultResponse<R, E> {
    return when(this) {
        is ResultResponse.Error -> ResultResponse.Error(error)
        is ResultResponse.Success -> ResultResponse.Success(map(data))
    }
}

fun <T, E: Error> ResultResponse<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}

inline fun <T, E: Error> ResultResponse<T, E>.onSuccess(action: (T) -> Unit): ResultResponse<T, E> {
    return when(this) {
        is ResultResponse.Error -> this
        is ResultResponse.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T, E: Error> ResultResponse<T, E>.onError(action: (E) -> Unit): ResultResponse<T, E> {
    return when(this) {
        is ResultResponse.Error -> {
            action(error)
            this
        }
        is ResultResponse.Success -> this
    }
}

typealias EmptyResult<E> = ResultResponse<Unit, E>