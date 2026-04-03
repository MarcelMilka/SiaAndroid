package eu.project.design_system.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import eu.project.design_system.theme.Radius
import eu.project.design_system.theme.SiaTheme
import eu.project.design_system.theme.Space

sealed class BottomSheetState<out T> {
    data object Hidden : BottomSheetState<Nothing>()
    data class Shown<T>(val data: T) : BottomSheetState<T>()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BottomSheet(
    state: BottomSheetState<T>,
    onDismissRequest: () -> Unit,
    testTag: String,
    content: @Composable ColumnScope.(T) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val isVisible = state is BottomSheetState.Shown

    LaunchedEffect(isVisible) {
        if (isVisible) sheetState.show() else sheetState.hide()
    }

    if (isVisible || sheetState.isVisible) {
        ModalBottomSheet(
            modifier = Modifier.testTag(testTag),
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            containerColor = SiaTheme.color.surface.background,
            shape = RoundedCornerShape(topStart = Radius.R24.value, topEnd = Radius.R24.value),
            dragHandle = {}
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Space.S16.value, vertical = Space.S24.value),
            ) {
                if (state is BottomSheetState.Shown) {
                    content(state.data)
                }
            }
        }
    }
}