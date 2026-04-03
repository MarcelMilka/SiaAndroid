package eu.project.sia

import eu.project.common.navigation.Navigation

/**
 * Sealed class representing the initialization state of the application. Contains Pending state (shown during splash
 * screen) and Ready state (contains the determined start route for navigation).
 */
internal sealed class ApplicationStartupState {
    object Pending: ApplicationStartupState()
    data class Ready(val startRoute: Navigation): ApplicationStartupState()
}