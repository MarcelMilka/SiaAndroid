package eu.project.auth.authz

import eu.project.auth.token.AccessToken

/**
 * Provides access tokens.
 */
interface AuthzManager {

    fun getAccessToken(): AccessToken?
}