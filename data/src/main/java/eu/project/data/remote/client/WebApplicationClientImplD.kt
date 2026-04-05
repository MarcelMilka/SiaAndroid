package eu.project.data.remote.client

import eu.project.data.BuildConfig
import eu.project.data.remote.endpoint.TestEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

internal class WebApplicationClientImplD @Inject constructor(
    okHttpClient: ApplicationOkHttpClient,
): WebApplicationClient {

    private val apiBaseUrl = BuildConfig.API_BASE_URL

    private val retrofit = Retrofit.Builder()
        .baseUrl(apiBaseUrl)
        .client(okHttpClient.client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override val testEndpoint: TestEndpoint
        get() = retrofit.create<TestEndpoint>()
}