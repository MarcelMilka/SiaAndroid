package eu.project.auth.credentialManager

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import eu.project.auth.BuildConfig
import eu.project.auth.nonce.HashedNonce
import eu.project.auth.token.GoogleIdToken
import javax.inject.Inject

/**
 * Implementation of GoogleCredentialManager.
 */
internal class GoogleCredentialManagerImpl @Inject constructor(private val context: Context): GoogleCredentialManager {

    private val credentialManager = CredentialManager.create(this.context)
    private val googleWebClientId = BuildConfig.GOOGLE_WEB_CLIENT_ID

    private fun generateGoogleIdOption(hashedNonce: HashedNonce): GetGoogleIdOption {
        return GetGoogleIdOption.Builder()

            // check if the user has any accounts that previously have been used to sign in to the app (must be set to true)
            .setFilterByAuthorizedAccounts(false)

            // let the authentication server verify the application that's using the API
            .setServerClientId(googleWebClientId)

            // sign in automatically if the user:
            // - hasn't signed out
            // - there's only one Google account
            // - the user hasn't disabled automatic sign-in in their Google Account Settings
            .setAutoSelectEnabled(true)

            // prevent replay attacks
            .setNonce(hashedNonce.value)
            .build()
    }

    private fun generateGetCredentialRequest(googleIdOption: GetGoogleIdOption): GetCredentialRequest {
        return GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

    override suspend fun getGoogleIdToken(hashedNonce: HashedNonce): GoogleIdToken {
        val googleIdOption = generateGoogleIdOption(hashedNonce)
        val request = generateGetCredentialRequest(googleIdOption)

        val result = credentialManager.getCredential(
            context = context,
            request = request
        )
        val credential = result.credential
        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

        val rawGoogleIdToken = googleIdTokenCredential.idToken
        val googleIdToken = GoogleIdToken(rawGoogleIdToken)
        return googleIdToken
    }
}