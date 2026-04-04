package eu.project.authenticate.welcomeScreen.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import eu.project.authenticate.welcomeScreen.intent.WelcomeScreenIntent
import eu.project.authenticate.welcomeScreen.screen.content.WelcomeScreenIdleContent
import eu.project.authenticate.welcomeScreen.state.WelcomeScreenState
import eu.project.design_system.TestTag
import eu.project.design_system.content.ContentHolder
import eu.project.design_system.content.LoadingContent
import eu.project.ui.R

@Composable
internal fun WelcomeScreen(
    state: WelcomeScreenState,
    handleIntent: (WelcomeScreenIntent) -> Unit
) {
    ContentHolder(testTag = TestTag.WelcomeScreen.SCREEN) {
        when(state) {
            WelcomeScreenState.Idle -> {
                WelcomeScreenIdleContent {
                    handleIntent(WelcomeScreenIntent.ClickContinueWithGoogle)
                }
            }
            WelcomeScreenState.Pending, WelcomeScreenState.Success -> {
                LoadingContent(
                    supportingText = stringResource(R.string.welcome_screen___loading),
                    testTag = TestTag.WelcomeScreen.LOADING_CONTENT
                )
            }
        }
    }
}