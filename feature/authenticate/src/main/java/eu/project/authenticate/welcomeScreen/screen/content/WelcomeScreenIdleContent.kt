package eu.project.authenticate.welcomeScreen.screen.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import eu.project.design_system.TestTag
import eu.project.design_system.component.VerticalSpacer
import eu.project.design_system.component.button.ButtonSize
import eu.project.design_system.component.button.ButtonState
import eu.project.design_system.component.button.FilledButton
import eu.project.design_system.component.button.FilledButtonType
import eu.project.design_system.content.SplitContent
import eu.project.design_system.theme.SiaTheme
import eu.project.design_system.theme.Space
import eu.project.ui.R

@Composable
internal fun WelcomeScreenIdleContent(
    onClickContinueWithGoogle: () -> Unit
) {
    SplitContent(
        testTag = TestTag.WelcomeScreen.IDLE_CONTENT,
        upperVerticalArrangement = Arrangement.Bottom,
        lowerVerticalArrangement = Arrangement.Bottom,
        upperContent = {
            Text(
                text = stringResource(R.string.welcome_screen___sia),
                color = SiaTheme.color.text.primary,
                style = SiaTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.85f)
            )

            VerticalSpacer(Space.S4)

            Text(
                text = stringResource(R.string.welcome_screen___sia_explanation),
                color = SiaTheme.color.text.secondary,
                style = SiaTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.85f)
            )
        },
        lowerContent = {
            FilledButton(
                onClick = onClickContinueWithGoogle,
                label = stringResource(R.string.welcome_screen___continue_with_google),
                type = FilledButtonType.Primary,
                size = ButtonSize.Medium,
                state = ButtonState.Enabled,
                testTag = TestTag.WelcomeScreen.IDLE_CONTENT_BUTTON,
            )
        }
    )
}