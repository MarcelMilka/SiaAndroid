package eu.project.auth.nonce

import java.security.MessageDigest
import java.security.SecureRandom

/**
 * Generates both hashed and raw versions of nonce wrapped by `NonceSet`.
 */
object NonceGenerator {

    fun generateNonce(): NonceSet {

        // create a base to generate a random value
        val randomBytes = ByteArray(32)
        SecureRandom().nextBytes(randomBytes)

        // create raw nonce
        val rawNonce: String = randomBytes.joinToString("") { "%02x".format(it) }

        // create hashed nonce
        val digest = MessageDigest.getInstance("SHA-256").digest(rawNonce.toByteArray())
        val hashedNonce: String = digest.joinToString("") { "%02x".format(it) }

        return NonceSet(
            rawNonce = RawNonce(value = rawNonce),
            hashedNonce = HashedNonce(value = hashedNonce)
        )
    }
}