package com.dedechandran.storiesapps.data

import com.dedechandran.storiesapps.data.local.SessionManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sessionManager: SessionManager) : Interceptor{

    private val scope = CoroutineScope(Dispatchers.Default)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = sessionManager.getLoginSession()?.token
        if (token.isNullOrEmpty().not()) {
            request.header("Authorization", "Bearer $token")
        }
        return chain.proceed(request.build())
    }


}