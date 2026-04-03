package eu.project.auth.nonce

/**
 * Wrapper of the data classes `RawNonce` and `HashedNonce`.
 */
data class NonceSet(
    val rawNonce: RawNonce,
    val hashedNonce: HashedNonce
)