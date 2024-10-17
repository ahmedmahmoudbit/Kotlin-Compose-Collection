package com.geecoders.retrofit_compose.core.network

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): ResultResponse<T, NetworkError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                ResultResponse.Success(response.body<T>())
            } catch(e: NoTransformationFoundException) {
                ResultResponse.Error(NetworkError.SERIALIZATION)
            }
        }
        408 -> ResultResponse.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> ResultResponse.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> ResultResponse.Error(NetworkError.SERVER_ERROR)
        else -> ResultResponse.Error(NetworkError.UNKNOWN)
    }
}