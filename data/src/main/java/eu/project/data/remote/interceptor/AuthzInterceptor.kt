package eu.project.data.remote.interceptor

import eu.project.auth.authz.AuthzManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthzInterceptor @Inject constructor(
    private val authzManager: AuthzManager
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        // try to get JWT access token to be passed into the 'Authorization' header
        val rawAccessToken =
            authzManager.getAccessToken()?.value ?:
            return chain.proceed(chain.request()) // no token available, proceed without authorization

        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $rawAccessToken")
            .build()

        return chain.proceed(modifiedRequest)
    }
}