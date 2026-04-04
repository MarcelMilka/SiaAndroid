package eu.project.authenticate.welcomeScreen.vm

import app.cash.turbine.test
import eu.project.auth.authn.AuthnManager
import eu.project.auth.result.SignInWithGoogleResult
import eu.project.authenticate.welcomeScreen.intent.WelcomeScreenIntent
import eu.project.authenticate.welcomeScreen.state.WelcomeScreenState
import eu.project.common.crashlytics.CrashlyticsManager
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainDispatcherRule(
    val dispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
    }
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class WelcomeScreenViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val authnManager = mockk<AuthnManager>(relaxed = true)
    private val crashlyticsManager = mockk<CrashlyticsManager>(relaxed = true)

    private lateinit var viewModel: WelcomeScreenViewModel

    @Before
    fun setup() {
        viewModel = WelcomeScreenViewModel(authnManager, crashlyticsManager)
    }

    @Test
    fun `initial state is Idle`() = runTest {
        viewModel.screenState.test {
            assertEquals(WelcomeScreenState.Idle, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        confirmVerified()
    }

    @Test
    fun `ClickContinueWithGoogle sets state to Success when sign in succeeds`() = runTest {
        // Prepare mock
        coEvery { authnManager.signInWithGoogle() } returns SignInWithGoogleResult.Success

        viewModel.screenState.test {

            skipItems(1)

            // Trigger intent
            viewModel.handleIntent(WelcomeScreenIntent.ClickContinueWithGoogle)

            // Verify sequence: Pending -> Success
            assertEquals(WelcomeScreenState.Pending, awaitItem())
            assertEquals(WelcomeScreenState.Success, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { authnManager.signInWithGoogle() }
        confirmVerified()
    }

    @Test
    fun `ClickContinueWithGoogle returns to Idle when sign in is cancelled`() = runTest {
        // Prepare mock
        coEvery { authnManager.signInWithGoogle() } returns SignInWithGoogleResult.Failure.Cancelled

        viewModel.screenState.test {
            assertEquals(WelcomeScreenState.Idle, awaitItem())

            viewModel.handleIntent(WelcomeScreenIntent.ClickContinueWithGoogle)

            assertEquals(WelcomeScreenState.Pending, awaitItem())
            assertEquals(WelcomeScreenState.Idle, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { authnManager.signInWithGoogle() }
        confirmVerified()
    }

    @Test
    fun `ClickContinueWithGoogle records exception and returns to Idle on Unknown failure`() = runTest {
        // Prepare mock
        val testException = RuntimeException("Auth failed")
        coEvery { authnManager.signInWithGoogle() } returns SignInWithGoogleResult.Failure.Unknown(testException)

        viewModel.screenState.test {
            assertEquals(WelcomeScreenState.Idle, awaitItem())

            viewModel.handleIntent(WelcomeScreenIntent.ClickContinueWithGoogle)

            assertEquals(WelcomeScreenState.Pending, awaitItem())
            assertEquals(WelcomeScreenState.Idle, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) {
            authnManager.signInWithGoogle()
            crashlyticsManager.recordException(testException)
        }
        confirmVerified()
    }
}