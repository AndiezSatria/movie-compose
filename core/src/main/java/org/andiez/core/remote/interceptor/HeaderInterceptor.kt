package org.andiez.core.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import org.andiez.core.BuildConfig

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + BuildConfig.API_KEY).build()
        return chain.proceed(request)
    }
}
