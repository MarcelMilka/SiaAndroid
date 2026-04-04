package eu.project.auth.client

import eu.project.auth.BuildConfig
import io.github.jan.supabase.createSupabaseClient
import javax.inject.Inject
import io.github.jan.supabase.auth.Auth

/**
 * Configures and provides the Supabase Kotlin SDK client with authentication support.
 */
internal class SupabaseClient @Inject constructor() {

    private val supabaseDatabaseUrl = BuildConfig.SUPABASE_DATABASE_URL
    private val supabasePublishableKey = BuildConfig.SUPABASE_PUBLISHABLE_KEY

    val client = createSupabaseClient(
        supabaseUrl = supabaseDatabaseUrl,
        supabaseKey = supabasePublishableKey,
        builder = {
            install(Auth.Companion)
        }
    )
}