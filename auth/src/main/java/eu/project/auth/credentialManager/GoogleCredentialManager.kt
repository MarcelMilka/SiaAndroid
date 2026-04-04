package eu.project.auth.credentialManager

import eu.project.auth.nonce.HashedNonce
import eu.project.auth.token.GoogleIdToken

/**
 * Retrieves a Google ID token for authentication, using the provided hashed nonce for security validation.
 */
interface GoogleCredentialManager {

    suspend fun getGoogleIdToken(hashedNonce: HashedNonce): GoogleIdToken
}