package eu.project.data.remote.client

import eu.project.data.remote.interceptor.AuthzInterceptor
import eu.project.data.remote.interceptor.LoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ApplicationOkHttpClient @Inject constructor(
    authzInterceptor: AuthzInterceptor,
    loggingInterceptor: LoggingInterceptor
) {

    val client = OkHttpClient.Builder()
        .addInterceptor(authzInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()
}