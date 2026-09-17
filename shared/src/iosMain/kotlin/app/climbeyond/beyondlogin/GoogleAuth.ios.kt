package app.climbeyond.beyondlogin

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

internal actual suspend fun requestGoogleIdToken(self: BeyondLogin): GoogleSignInResult {
    return suspendCancellableCoroutine { continuation ->
        self.viewService.listener.requestGoogleIdToken { idToken, nonce ->
            if (idToken != null && nonce != null) {
                continuation.resume(GoogleSignInResult.Success(idToken, nonce))
            } else {
                continuation.resume(GoogleSignInResult.Cancelled)
            }
        }
    }
}

internal actual suspend fun requestGoogleIdTokenSilently(self: BeyondLogin): GoogleSignInResult {
    // BeyondLogin does not control the host app's Google Sign-In integration on iOS - silently
    // triggering it on screen load isn't appropriate here, so this is a no-op.
    return GoogleSignInResult.Cancelled
}

internal actual fun googlePlatformConfigured(settings: Settings.Data): Boolean = true
