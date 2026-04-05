package eu.project.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.project.auth.authz.AuthzManager
import eu.project.data.remote.client.ApplicationOkHttpClient
import eu.project.data.remote.client.WebApplicationClient
import eu.project.data.remote.client.WebApplicationClientImplD
import eu.project.data.remote.interceptor.AuthzInterceptor
import eu.project.data.remote.interceptor.LoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ClientModule {

    @Provides
    @Singleton
    fun provideAuthzInterceptor(
        authzManager: AuthzManager
    ): AuthzInterceptor =
        AuthzInterceptor(authzManager)

    @Provides
    @Singleton
    fun provideApplicationOkHttpClient(
        authzInterceptor: AuthzInterceptor,
        loggingInterceptor: LoggingInterceptor
    ): ApplicationOkHttpClient =
        ApplicationOkHttpClient(
            authzInterceptor = authzInterceptor,
            loggingInterceptor = loggingInterceptor
        )

    @Provides
    @Singleton
    fun provideWebApplicationClient(
        applicationOkHttpClient: ApplicationOkHttpClient
    ): WebApplicationClient =
        WebApplicationClientImplD(
            okHttpClient = applicationOkHttpClient
        )
}