package eu.project.design_system.theme

import androidx.compose.ui.graphics.Color

internal object PrimitiveColorsDarkTheme {
    val neutral950 = Color(0xFF161616)
    val neutral900 = Color(0xFF1E1E1E)
    val neutral800 = Color(0xFF2A2A2A)
    val neutral300 = Color(0xFFB3B3B3)
    val neutral50 = Color(0xFFDBDBDB)
    val red500 = Color(0xFFE5484D)
    val yellow500 = Color(0xFFE5A448)
}

data class AppColors(
    val surface: SurfaceColors,
    val text: TextColors,
    val border: BorderColors,
    val icon: IconColors,
    val button: ButtonColors,
    val outlinedTextField: OutlinedTextFieldColors
)

data class SurfaceColors(
    val background: Color,
    val card: Color
)

data class TextColors(
    val primary: Color,
    val secondary: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val destructive: Color,
    val highlighted: Color,
)

data class BorderColors(
    val regular: Color,
    val destructive: Color
)

data class IconColors(
    val primary: Color,
    val secondary: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val destructive: Color
)

data class ButtonColors(
    val primary: Color,
    val secondary: Color,
)

data class OutlinedTextFieldColors(
    val text: TextFieldTextColors,val container: TextFieldContainerColors,
    val border: TextFieldBorderColors,
    val cursor: Color,
    val errorCursor: Color
) {
    data class TextFieldTextColors(
        val focused: Color,
        val unfocused: Color,
        val disabled: Color,
        val error: Color,
        val label: Color,
        val placeholder: Color
    )

    data class TextFieldContainerColors(
        val focused: Color,
        val unfocused: Color,
        val disabled: Color,
        val error: Color
    )

    data class TextFieldBorderColors(
        val focused: Color,
        val unfocused: Color,
        val disabled: Color,
        val error: Color
    )
}

fun siaDarkColors(): AppColors {
    val colors = PrimitiveColorsDarkTheme
    return AppColors(
        surface = SurfaceColors(
            background = colors.neutral900,
            card = colors.neutral950
        ),
        text = TextColors(
            primary = colors.neutral50,
            secondary = colors.neutral300,
            onPrimary = colors.neutral800,
            onSecondary = colors.neutral300,
            destructive = colors.red500,
            highlighted = colors.yellow500
        ),
        border = BorderColors(
            regular = colors.neutral800,
            destructive = colors.red500
        ),
        icon = IconColors(
            primary = colors.neutral50,
            secondary = colors.neutral300,
            onPrimary = colors.neutral800,
            onSecondary = colors.neutral300,
            destructive = colors.red500
        ),
        button = ButtonColors(
            primary = colors.neutral50,
            secondary = colors.neutral950
        ),
        outlinedTextField = OutlinedTextFieldColors(
            text = OutlinedTextFieldColors.TextFieldTextColors(
                focused = colors.neutral50,
                unfocused = colors.neutral300.copy(alpha = Opacity.Unfocused.value),
                disabled = colors.neutral50.copy(alpha = Opacity.Disabled.value),
                error = colors.red500,
                label = colors.neutral300,
                placeholder = colors.neutral300
            ),
            container = OutlinedTextFieldColors.TextFieldContainerColors(
                focused = colors.neutral950,
                unfocused = colors.neutral950.copy(alpha = Opacity.Unfocused.value),
                disabled = colors.neutral950.copy(alpha = Opacity.Disabled.value),
                error = colors.red500.copy(alpha = Opacity.Disabled.value)
            ),
            border = OutlinedTextFieldColors.TextFieldBorderColors(
                focused = colors.neutral50,
                unfocused = colors.neutral50.copy(alpha = Opacity.Unfocused.value),
                disabled = colors.neutral50.copy(alpha = Opacity.Disabled.value),
                error = colors.red500.copy(alpha = Opacity.Disabled.value)
            ),
            cursor = colors.neutral50,
            errorCursor = colors.red500
        )
    )
}