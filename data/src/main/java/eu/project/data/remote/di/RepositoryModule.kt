package eu.project.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.project.data.remote.client.WebApplicationClient
import eu.project.data.remote.repository.TestRepository
import eu.project.data.remote.repository.TestRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RepositoryModule {

    @Provides
    @Singleton
    fun provideTestRepository (
        webApplicationClient: WebApplicationClient
    ): TestRepository =
        TestRepositoryImpl(webApplicationClient.testEndpoint)
}