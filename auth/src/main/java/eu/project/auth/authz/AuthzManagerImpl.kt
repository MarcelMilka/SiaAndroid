package eu.project.auth.authz

import eu.project.auth.client.SupabaseClient
import eu.project.auth.token.AccessToken
import io.github.jan.supabase.auth.auth
import javax.inject.Inject

/**
 * Implementation of `AuthzManager` that uses `SupabaseClient` to retrieve access token.
 */
internal class AuthzManagerImpl @Inject constructor(supabaseClient: SupabaseClient) : AuthzManager {

    private val client = supabaseClient.client

    override fun getAccessToken(): AccessToken? {

        // try to retrieve current access token, if it's null, return null
        val rawAccessToken = client.auth.currentAccessTokenOrNull() ?: return null
        return AccessToken(rawAccessToken)
    }
}