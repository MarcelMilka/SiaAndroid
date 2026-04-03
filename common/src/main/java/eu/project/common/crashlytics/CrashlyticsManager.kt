package eu.project.common.crashlytics

/**
 * Abstraction over the crash reporting SDK.
 */
interface CrashlyticsManager {

    /**
     * Appends a message to the in-memory breadcrumb buffer.
     */
    fun logBreadcrumb(message: String)

    /**
     * Records a non-fatal exception and flushes the breadcrumb buffer
     * into the report.
     */
    fun recordException(throwable: Throwable, extras: Map<String, String> = emptyMap())

    /**
     * Associates subsequent reports with a specific user.
     */
    fun setUserContext(userId: String, email: String)
}