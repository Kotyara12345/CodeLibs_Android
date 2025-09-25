package com.codelibs.core_network.utils

import com.codelibs.core_storage.CredentialsStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class BasicAuthInterceptor @Inject constructor(
    private val credentials: CredentialsStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val username = credentials.getLogin()
        val password = credentials.getPassword()

        if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
            val header = buildBasicAuthHeader(username, password)
            requestBuilder.addHeader("Authorization", header)
        }
        return chain.proceed(requestBuilder.build())
    }
}