package eu.project.design_system.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import eu.project.design_system.TestTag
import eu.project.design_system.component.LoadingIndicator
import eu.project.design_system.component.VerticalSpacer
import eu.project.design_system.theme.SiaTheme
import eu.project.design_system.theme.Space

@Composable
fun LoadingContent(
    supportingText: String,
    testTag: String
) {
    SplitContent(
        upperVerticalArrangement = Arrangement.Bottom,
        lowerVerticalArrangement = Arrangement.Bottom,
        testTag = testTag,
        upperContent = {
            LoadingIndicator(
                testTag = TestTag.Component.loadingIndicator(testTag)
            )

            VerticalSpacer(Space.S4)

            Text(
                text = supportingText,
                style = SiaTheme.typography.bodyMedium,
                color = SiaTheme.color.text.secondary
            )
        },
        lowerContent = {}
    )
}