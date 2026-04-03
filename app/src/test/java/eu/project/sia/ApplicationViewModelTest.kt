package eu.project.sia

import app.cash.turbine.test
import eu.project.auth.authn.AuthnManager
import eu.project.common.navigation.Navigation
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainDispatcherRule(
    val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class ApplicationViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val authnManager = mockk<AuthnManager>(relaxed = true)

    private lateinit var viewModel: ApplicationViewModel



    @Test
    fun `sets Ready with RouteAuthenticated when user is signed in`() = runTest {
        // set up
        coEvery { authnManager.restoreSession() } just Runs
        coEvery { authnManager.isSignedIn() } returns true

        // call
        viewModel = ApplicationViewModel(authnManager)

        // verify
        viewModel.applicationStartupState.test {
            assertEquals(
                ApplicationStartupState.Ready(Navigation.Authenticated.RouteAuthenticated),
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `sets Ready with RouteUnauthenticated when user is not signed in`() = runTest {
        // set up
        coEvery { authnManager.restoreSession() } just Runs
        coEvery { authnManager.isSignedIn() } returns false

        // call
        viewModel = ApplicationViewModel(authnManager)

        // verify
        viewModel.applicationStartupState.test {
            assertEquals(
                ApplicationStartupState.Ready(Navigation.Unauthenticated.RouteUnauthenticated),
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `sets Ready with InitializationErrorScreen when restoreSession fails`() = runTest {
        // set up
        coEvery { authnManager.restoreSession() } throws RuntimeException("Network error")

        // call
        viewModel = ApplicationViewModel(authnManager)

        // verify
        viewModel.applicationStartupState.test {
            assertEquals(
                ApplicationStartupState.Ready(Navigation.InitializationErrorScreen),
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `sets Ready with InitializationErrorScreen when isSignedIn throws`() = runTest {
        // set up
        coEvery { authnManager.restoreSession() } just Runs
        coEvery { authnManager.isSignedIn() } throws IllegalStateException("Corrupted local state")

        // call
        viewModel = ApplicationViewModel(authnManager)

        // verify
        viewModel.applicationStartupState.test {
            assertEquals(
                ApplicationStartupState.Ready(Navigation.InitializationErrorScreen),
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}