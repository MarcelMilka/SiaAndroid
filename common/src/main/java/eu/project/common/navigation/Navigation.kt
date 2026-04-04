package eu.project.common.navigation

import kotlinx.serialization.Serializable

sealed class Navigation {

    sealed class Unauthenticated: Navigation() {
        @Serializable
        data object RouteUnauthenticated: Unauthenticated()

        @Serializable
        data object WelcomeScreen: Unauthenticated()
    }

    sealed class Authenticated: Navigation() {
        @Serializable
        data object RouteAuthenticated: Authenticated()

        @Serializable
        data object HomeScreen: Authenticated()
    }

    @Serializable
    data object InitializationErrorScreen: Navigation()



    sealed class Saved: Navigation() {

        @Serializable
        data object RouteSaved: Saved()

            @Serializable
            data object SavedWordsScreen: Saved()

            @Serializable
            data object ExportWordsScreen: Saved()

            @Serializable
            data class ExportResultScreen(val exportSettingsSerialized: String): Saved()
    }

    sealed class Transcribe: Navigation() {

        @Serializable
        data object RouteTranscribe: Transcribe()

            @Serializable
            data object SelectAudioScreen: Transcribe()
    }
}