package eu.project.auth.authn

import androidx.credentials.exceptions.GetCredentialCancellationException
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import eu.project.auth.client.SupabaseClient
import eu.project.auth.credentialManager.GoogleCredentialManager
import eu.project.auth.nonce.NonceGenerator
import eu.project.auth.result.SignInWithGoogleResult
import eu.project.auth.user.User
import io.github.jan.supabase.auth.SignOutScope
import java.util.UUID
import javax.inject.Inject

/**
 * Implementation of `AuthnManager` that uses `SupabaseClient` to perform auth-related tasks.
 */
internal class AuthnManagerImpl @Inject constructor(
    supabaseClient: SupabaseClient,
    private val googleCredentialManager: GoogleCredentialManager
): AuthnManager {

    private val client = supabaseClient.client

    override suspend fun signInWithGoogle(): SignInWithGoogleResult {
        return try {
            val nonceSet = NonceGenerator.generateNonce()

            val googleIdToken = googleCredentialManager.getGoogleIdToken(nonceSet.hashedNonce)

            client.auth.signInWith(IDToken) {
                this.idToken = googleIdToken.value
                this.provider = Google
                this.nonce = nonceSet.rawNonce.value
            }
            SignInWithGoogleResult.Success
        }
        catch (e: Exception) {
            when (e) {
                is GetCredentialCancellationException -> SignInWithGoogleResult.Failure.Cancelled
                else -> SignInWithGoogleResult.Failure.Unknown(e)
            }
        }
    }

    override fun getUser(): User? {
        val id = client.auth.currentUserOrNull()?.id ?: return null
        val email = client.auth.currentUserOrNull()?.email ?: return null

        return try {
            User(
                id = UUID.fromString(id),
                email = email
            )
        }
        catch (e: IllegalArgumentException) {
            null
        }
    }

    override suspend fun restoreSession() {

        client.auth.currentUserOrNull()?.id

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