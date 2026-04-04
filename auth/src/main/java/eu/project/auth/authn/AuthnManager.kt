package eu.project.auth.authn

import eu.project.auth.result.SignInWithGoogleResult
import eu.project.auth.user.User

/**
 * Handles user authentication and session management. Provides methods to sign in with Google,
 * restore existing sessions, check sign-in status, and sign out.
 */
interface AuthnManager {

    suspend fun signInWithGoogle(): SignInWithGoogleResult

    fun getUser(): User?

    suspend fun restoreSession()

    suspend fun isSignedIn(): Boolean

    suspend fun signOut()
}