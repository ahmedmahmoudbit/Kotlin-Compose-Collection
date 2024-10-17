package com.geecoders.retrofit_compose.core.network

import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext


suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): ResultResponse<T, NetworkError> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        return ResultResponse.Error(NetworkError.NO_INTERNET)
    } catch(e: SerializationException) {
        return ResultResponse.Error(NetworkError.SERIALIZATION)
    } catch(e: Exception) {
        coroutineContext.ensureActive()
        return ResultResponse.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}