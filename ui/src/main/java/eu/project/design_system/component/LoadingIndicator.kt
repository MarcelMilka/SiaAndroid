package eu.project.design_system.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import eu.project.design_system.theme.Opacity
import eu.project.design_system.theme.SiaTheme

enum class LoadingIndicatorType { Primary, Secondary }

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    type: LoadingIndicatorType = LoadingIndicatorType.Primary,
    testTag: String
) {
    CircularProgressIndicator(
        modifier = modifier
            .testTag(testTag),
        color = when(type) {
            LoadingIndicatorType.Primary -> SiaTheme.color.icon.primary
            LoadingIndicatorType.Secondary -> SiaTheme.color.icon.onPrimary.copy(alpha = Opacity.Disabled.value)
        },
        trackColor = when(type) {
            LoadingIndicatorType.Primary -> SiaTheme.color.icon.secondary
            LoadingIndicatorType.Secondary -> SiaTheme.color.icon.onSecondary.copy(alpha = Opacity.Disabled.value)
        },
        strokeCap = StrokeCap.Round
    )
}