package eu.project.design_system

/**
 * Central registry of accessibility content descriptions for use with
 * [androidx.compose.ui.Modifier.semantics] `contentDescription` and
 * [androidx.compose.ui.Modifier.clearAndSetSemantics].
 *
 * ## Purpose
 * Content descriptions are read aloud by TalkBack and other accessibility
 * services. Centralising them ensures consistency across the UI and makes
 * accessibility audits and updates straightforward.
 *
 * ## Naming conventions
 * - Mirrors the structure of [TestTag] exactly — same object hierarchy,
 *   same element names — so the two files stay in sync.
 * - String values are **human-readable sentences** (capitalised, no trailing
 *   period) that TalkBack can read naturally, e.g. `"Navigate back"`.
 * - Do **not** include the screen name in the string value; the OS provides
 *   enough context. Keep descriptions short (≤ 5 words where possible).
 *
 * ## Usage
 * ```kotlin
 * Icon(
 *     painter = painterResource(R.drawable.ic_back),
 *     contentDescription = ContentDescription.HomeScreen.BACK_BUTTON
 * )
 * ```
 *
 * ## When to provide a description
 * - **Always** for actionable elements (buttons, icons, FABs).
 * - **Always** for images that convey meaning.
 * - Set to `null` for purely decorative elements so TalkBack skips them.
 *
 * @see TestTag for UI test tag constants.
 */
object ContentDescription {

    object Icon {
        const val ERROR = "Icon Error"
        const val SUCCESS = "Icon Success"
    }
}