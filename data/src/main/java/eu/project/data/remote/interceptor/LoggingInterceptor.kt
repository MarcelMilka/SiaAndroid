package eu.project.data.remote.interceptor

import eu.project.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoggingInterceptor @Inject constructor() : Interceptor {

    private val logger = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            }
            else {
                HttpLoggingInterceptor.Level.NONE
            }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return logger.intercept(chain)
    }
}