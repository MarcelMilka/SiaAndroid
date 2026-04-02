package eu.project.design_system

/**
 * Central registry of UI test tags for use with [androidx.compose.ui.Modifier.testTag].
 *
 * ## Purpose
 * Provides a single source of truth for all test tag strings used in UI tests
 * (Espresso, Compose UI Test). Centralising them prevents typos, silent collisions,
 * and makes refactoring safe — change the constant once, tests follow automatically.
 *
 * ## Naming conventions
 * - Object names use **PascalCase** and reflect the screen or component name
 *   (e.g. [HomeScreen], [ProductCard]).
 * - Constant names use **SCREAMING_SNAKE_CASE** (e.g. `SUBMIT_BUTTON`).
 * - String values use **snake_case** and are prefixed with their full path to
 *   guarantee global uniqueness (e.g. `"home_screen_submit_button"`).
 *
 * ## Structure rules
 * - **Screens** → nest a dedicated object per screen directly inside [TestTag].
 * - **Shared / reusable components** → nest inside a dedicated object and expose
 *   a function that accepts a unique identifier (e.g. item ID) to avoid collisions
 *   when the component appears multiple times in the hierarchy.
 * - **Do not** define raw string literals outside this file. Always reference a
 *   constant from here.
 *
 * ## Usage
 * ```kotlin
 * // In a composable:
 * Modifier.testTag(TestTag.HomeScreen.SUBMIT_BUTTON)
 *
 * // In a UI test:
 * composeTestRule
 *     .onNodeWithTag(TestTag.HomeScreen.SUBMIT_BUTTON)
 *     .performClick()
 * ```
 *
 * ## Adding new tags
 * 1. Locate or create the matching nested object for the screen/component.
 * 2. Add a `const val` whose string value follows `<screen>_<element>` pattern.
 * 3. For list/grid items use a function: `fun root(id: String) = "my_card_root_$id"`.
 *
 * @see ContentDescription for accessibility label constants.
 */
object TestTag {

    object Component {
        fun loadingIndicator(parentTag: String) = "${parentTag}_loading_indicator"
        fun animatedContent(parentTag: String) = "${parentTag}_animated_content"

        fun dialogConfirmButton(parentTag: String) = "${parentTag}_confirm_text_button"
        fun dialogDismissButton(parentTag: String) = "${parentTag}_dismiss_text_button"
    }
}