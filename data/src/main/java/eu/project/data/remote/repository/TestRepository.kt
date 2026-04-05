package eu.project.data.remote.repository

interface TestRepository {
    suspend fun test(): Result<String>
}