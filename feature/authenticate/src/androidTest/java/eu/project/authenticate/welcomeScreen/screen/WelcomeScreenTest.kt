package eu.project.authenticate.welcomeScreen.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import eu.project.authenticate.welcomeScreen.intent.WelcomeScreenIntent
import eu.project.authenticate.welcomeScreen.state.WelcomeScreenState
import eu.project.design_system.TestTag
import eu.project.ui.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class WelcomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var capturedIntent: WelcomeScreenIntent? = null
    private val context = InstrumentationRegistry.getInstrumentation().targetContext



//- WelcomeScreenState.Idle Tests ------------------------------------------------------------------

    @Test
    fun welcomeScreen_idleState_displaysIdleContent() {
        setupWelcomeScreen(WelcomeScreenState.Idle)

        composeTestRule
            .onNodeWithTag(TestTag.WelcomeScreen.IDLE_CONTENT)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.welcome_screen___sia))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.welcome_screen___sia_explanation))
            .assertIsDisplayed()
    }

    @Test
    fun welcomeScreen_idleState_continueWithGoogleClick_triggersIntent() {
        setupWelcomeScreen(WelcomeScreenState.Idle)

        composeTestRule
            .onNodeWithTag(TestTag.WelcomeScreen.IDLE_CONTENT_BUTTON)
            .performClick()

        assertEquals(WelcomeScreenIntent.ClickContinueWithGoogle, capturedIntent)
    }



//- WelcomeScreenState.Idle Pending & Success Tests ------------------------------------------------

    @Test
    fun welcomeScreen_pendingState_displaysLoadingContent() {
        setupWelcomeScreen(WelcomeScreenState.Pending)

        composeTestRule
            .onNodeWithTag(TestTag.WelcomeScreen.LOADING_CONTENT)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.welcome_screen___loading))
            .assertIsDisplayed()
    }

    @Test
    fun welcomeScreen_successState_displaysLoadingContent() {
        setupWelcomeScreen(WelcomeScreenState.Success)

        composeTestRule
            .onNodeWithTag(TestTag.WelcomeScreen.LOADING_CONTENT)
            .assertIsDisplayed()
    }



//- Isolated Tests ---------------------------------------------------------------------------------

    @Test
    fun welcomeScreenIdleContent_displaysSecondaryText() {
        var clicked = false
        composeTestRule.setContent {
            eu.project.authenticate.welcomeScreen.screen.content.WelcomeScreenIdleContent(
                onClickContinueWithGoogle = { clicked = true }
            )
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.welcome_screen___sia))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.welcome_screen___sia_explanation))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTag.WelcomeScreen.IDLE_CONTENT_BUTTON)
            .performClick()

        assertTrue(clicked)
    }



//- Helper-------- ---------------------------------------------------------------------------------

    private fun setupWelcomeScreen(state: WelcomeScreenState) {
        composeTestRule.setContent {
            WelcomeScreen(
                state = state,
                handleIntent = { intent -> capturedIntent = intent }
            )
        }
    }
}