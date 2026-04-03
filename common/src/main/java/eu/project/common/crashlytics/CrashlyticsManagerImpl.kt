package eu.project.common.crashlytics

import com.google.firebase.crashlytics.FirebaseCrashlytics
import jakarta.inject.Inject

class CrashlyticsManagerImpl @Inject constructor(): CrashlyticsManager {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun logBreadcrumb(message: String) {
        crashlytics.log(message)
    }

    override fun recordException(throwable: Throwable, extras: Map<String, String>) {
        extras.forEach { (k, v) -> crashlytics.setCustomKey(k, v) }
        crashlytics.recordException(throwable)
    }

    override fun setUserContext(userId: String, email: String) {
        crashlytics.setUserId(userId)
    }
}