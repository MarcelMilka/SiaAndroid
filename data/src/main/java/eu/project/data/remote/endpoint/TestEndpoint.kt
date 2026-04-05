package eu.project.data.remote.endpoint

import retrofit2.Response
import retrofit2.http.GET

internal interface TestEndpoint {

    @GET("/")
    suspend fun test(): Response<String>
}