package eu.project.auth.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.project.auth.authn.AuthnManager
import eu.project.auth.authn.AuthnManagerImpl
import eu.project.auth.client.SupabaseClient
import eu.project.auth.credentialManager.GoogleCredentialManager
import eu.project.auth.credentialManager.GoogleCredentialManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AuthModule {

    @Provides
    @Singleton
    fun provideAuthManager(
        supabaseClient: SupabaseClient,
    ): AuthnManager =
        AuthnManagerImpl(supabaseClient)

    @Provides
    @Singleton
    fun provideGoogleCredentialManager(
        @ApplicationContext context: Context,
    ): GoogleCredentialManager =
        GoogleCredentialManagerImpl(context = context)
}