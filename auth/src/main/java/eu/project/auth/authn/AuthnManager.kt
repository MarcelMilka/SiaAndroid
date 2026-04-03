package eu.project.auth.authn

import eu.project.auth.nonce.RawNonce
import eu.project.auth.token.GoogleIdToken

/**
 * Handles user authentication and session management. Provides methods to sign in with Google,
 * restore existing sessions, check sign-in status, and sign out.
 */
interface AuthnManager {

    suspend fun signInWithGoogle(
        googleIdToken: GoogleIdToken,
        rawNonce: RawNonce
    )

    suspend fun restoreSession()

    suspend fun isSignedIn(): Boolean

    suspend fun signOut()
}