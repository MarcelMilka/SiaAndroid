package eu.project.authenticate.welcomeScreen.intent

internal sealed interface WelcomeScreenIntent {
    data object ClickContinueWithGoogle: WelcomeScreenIntent
}