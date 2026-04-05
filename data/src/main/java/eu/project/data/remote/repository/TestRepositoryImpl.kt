package eu.project.data.remote.repository

import eu.project.data.remote.endpoint.TestEndpoint
import retrofit2.Response
import javax.inject.Inject

internal class TestRepositoryImpl @Inject constructor(
    private val testEndpoint: TestEndpoint
): TestRepository {

    override suspend fun test(): Result<String> {
        return runCatching {
            val response: Response<String> = testEndpoint.test()
            if (response.isSuccessful) {
                response.body() ?: error("Empty response body")
            }
            else {
                throw IllegalStateException("HTTP ${response.code()}: ${response.errorBody()?.string()}")
            }
        }
    }
}