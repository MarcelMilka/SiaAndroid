package eu.project.authenticate.welcomeScreen.state

internal sealed interface WelcomeScreenState {
    data object Idle: WelcomeScreenState
    data object Pending: WelcomeScreenState
    data object Success: WelcomeScreenState
}