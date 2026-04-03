package eu.project.auth.authn

import eu.project.auth.nonce.RawNonce
import eu.project.auth.token.GoogleIdToken
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import eu.project.auth.client.SupabaseClient
import io.github.jan.supabase.auth.SignOutScope
import javax.inject.Inject

/**
 * Implementation of `AuthnManager` that uses `SupabaseClient` to perform auth-related tasks.
 */
internal class AuthnManagerImpl @Inject constructor(supabaseClient: SupabaseClient): AuthnManager {

    private val client = supabaseClient.client

    override suspend fun signInWithGoogle(
        googleIdToken: GoogleIdToken,
        rawNonce: RawNonce
    ) {

        // sign in using Google
        client.auth.signInWith(IDToken) {
            this.idToken = googleIdToken.value
            this.provider = Google
            this.nonce = rawNonce.value
        }
    }

    override suspend fun restoreSession() {

        // block the current coroutine until the plugin is initialized.
        // ensure the SessionStatus is set to Authenticated, NotAuthenticated or RefreshError
        client.auth.awaitInitialization()
    }

    override suspend fun isSignedIn(): Boolean {

        // try to retrieve the current session
        val session = client.auth.currentSessionOrNull()
        return session?.user != null
    }

    override suspend fun signOut() {

        // sign out only from the device the app is running on
        client.auth.signOut(scope = SignOutScope.LOCAL)
    }
}