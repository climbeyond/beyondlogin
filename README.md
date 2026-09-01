# BeyondLogin as an Kotlin multiplatform Compose UI for Ory Kratos identity and credential management  

<br>

![Kotlin](https://img.shields.io/badge/Kotlin-7f52ff?style=flat-square&logo=kotlin&logoColor=white)
![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin%20Multiplatform-4c8d3f?style=flat-square&logo=kotlin&logoColor=white)
![Compose Multiplatform](https://img.shields.io/badge/Jetpack%20Compose%20Multiplatform-000000?style=flat-square&logo=android&logoColor=white)

![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat)
![kotlin-version](https://img.shields.io/badge/kotlin-2.2.10-orange)  

<br>

## Features

Features include:
* Email registration
* Login via password and one-time-password code
* Password reset
* Google SSO (native, via Kratos' native OIDC login method - see [Google SSO setup](#google-sso-setup))

## Download
Android and iOS library is available through github packages

## Example

#### Bind logging
```
CoroutineScope(Dispatchers.Default).launch {
    BLLogger.event.collect {
        when (it.level) {
            BLLogger.LEVEL.VERBOSE -> // it.tag, it.message, it.thread
            BLLogger.LEVEL.DEBUG -> // it.tag, it.message, it.thread
            BLLogger.LEVEL.INFO -> // it.tag, it.message, it.thread
            BLLogger.LEVEL.WARNING -> // it.tag, it.message, it.thread
            BLLogger.LEVEL.ERROR -> // it.tag, it.message, it.thread
        }
    }
}
```

#### Android and iOS initialization
```
// Create expect/actual wrapper function to call these from iOS/Android to be able to initialize context in Android side
Settings.init(BeyondLoginPlatform(BeyondLoginPlatform([context)), Settings.Data([URL to kratos API]).apply {
    logLevel = Settings.ApiLogLevel.INFO
    logTag = "LOGIN"
})
```

#### ViewModel
```
// Create Model to handle messages from BeyondLogin and show the Compose UI
class LoginModel(platform: BeyondLoginPlatform, coroutine: CoroutineScope) :
            ScreenViewModel(coroutine), ViewService.Listener {

        val beyondLogin: BeyondLogin = BeyondLogin([BeyondLoginPlatform], this)

        override fun closeBeyondLogin() {
            // BeyondLogin UI has been closed - probably require some refresh to your Compose UI
        }

        override fun logOut(success: Boolean) {
            // Session logout has been success
        }

        override fun loggedClose(message: String) {
            // Current session has been closed
        }

        override fun loginActive(token: String) {
            // Current active token
        }

        override fun loginSuccess(
            data: SessionInfo, appSuccess: (success: String) -> Unit,
            appFailure: (message: String) -> Unit
        ) {
            // Login success
            // SessionInfo: data.id, data.token, data.expire
        }

        override fun registerError() {
            // Log and handle registration error
        }

        override fun registerSuccess(data: SessionInfo, appSuccess: (failure: String) -> Unit,
                appFailure: (message: String) -> Unit) {

            // Kratos account created
            // SessionInfo: data.id, data.token, data.expire
            //
            // You can now store the account, create separate account to another system etc.
        }

        override fun unknownException(message: String) {
            // Log unknown exceptions and possible handling of issues
        }
    }
```

#### Create Model and show the UI
```
// Start the UI in application
val model = LoginModel([BeyondLoginPlatform], coroutine)
model.beyondLogin.View()
```

### Setup & Gradle

```
dependencyResolutionManagement {
    repositories {
        maven {
            name = "Github Packages - Climbeyond"
            url = uri("https://maven.pkg.github.com/climbeyond/beyondlogin")
            credentials {
                // Github packages require logged in user
                username = "User"
                password = "Password"
            }
        }
    }
}
```

## Google SSO setup

BeyondLogin submits Google sign-in to Kratos via its native OIDC method (`method: oidc, provider: google`
with an `id_token` + `id_token_nonce`), the same way Kratos' own native/mobile samples do it. Your Kratos
instance must already have the `google` provider configured under OIDC in `kratos.yml`.

The "Continue with Google" button only appears on the login screen (it also covers first-time sign-up:
Kratos creates the identity automatically from the Google ID token's claims if none exists yet).

### Kratos configuration
Add an `oidc` block under `selfservice.methods`, alongside your other methods:
```yaml
selfservice:
  methods:
    oidc:
      enabled: true
      config:
        providers:
          - id: google           # must be exactly "google" - the client hardcodes this as the
                                  # provider name when submitting the oidc method
            provider: google
            client_id: <your-web-client-id>.apps.googleusercontent.com
            client_secret: <web-client-secret>
            mapper_url: base64://<base64-encoded jsonnet, see below>
            scope:
              - email
              - profile
            # Needed whenever a platform's ID token carries a different `aud` than client_id -
            # e.g. an iOS app using its own iOS-type OAuth client via GoogleSignIn-iOS. Android's
            # debug/release OAuth clients (SHA-1-scoped) never appear as `aud` and don't belong
            # here - see the Android section below.
            additional_id_token_audiences:
              - <your-ios-client-id>.apps.googleusercontent.com
```
`client_id` should be a Google OAuth 2.0 **"Web application"** client - that's also the value used as
`googleServerClientId` in Android setup below.

`mapper_url` is required and has no default; a minimal jsonnet mapper for an identity schema whose only
trait is `email`:
```jsonnet
local claims = std.extVar('claims');

{
  identity: {
    traits: {
      // Only accept a Google-verified email as an identifier, so an unverified address can't be
      // used to take over an existing account that uses the same email.
      [if 'email' in claims && claims.email_verified then 'email' else null]: claims.email,
    },
  },
}
```
Base64-encode it (`base64 -w0 mapper.jsonnet`) to inline it as shown above, or serve it via `file://`
or `https://` instead - see Kratos' docs on [OIDC provider mapping](https://www.ory.sh/docs/kratos/reference/configuration).

#### Account linking
If a Google sign-in's email matches an existing identity that doesn't have the `google` provider linked
yet, Kratos doesn't error out - it re-issues a fresh login flow (dropping the oidc option, offering only
the account's existing methods) with an info message asking the user to sign in with that method first.
BeyondLogin's `LoginView` already detects this and switches to the new flow automatically, prefilling the
identifier - so completing that fresh flow (e.g. entering the account's password) signs the user in and
Kratos automatically attaches the Google credential to that identity for next time. No extra client or
server work needed beyond the config above.

### Settings
Two `Settings.Data` fields control the feature, set in whatever `Settings.init(...)` call you already do:
```
Settings.init(BeyondLoginPlatform(...), Settings.Data([URL to kratos API]).apply {
    googleOidcEnabled = true                  // master switch - shows the button on both platforms
    googleServerClientId = "your-web-client-id.apps.googleusercontent.com"  // Android only
})
```
`googleOidcEnabled` alone controls the button on iOS. On Android, the button additionally requires
`googleServerClientId` to be set - both are needed there.

#### Android
Fully handled inside BeyondLogin using [Credential Manager](https://developer.android.com/identity/sign-in/credential-manager).
`googleServerClientId` must be the **same Web client ID** configured for the `google` provider in Kratos
(Credential Manager requires a Web-type client ID as the audience even on Android - the separate,
SHA-1-scoped Android OAuth clients you register per debug/release signing cert in Google Cloud Console are
only used by Play Services to verify the calling app; they're never referenced in code and never appear
as the ID token's `aud`, so they don't go into Kratos' `additional_id_token_audiences` either).

Note: pass an Activity `Context` when constructing `BeyondLoginPlatform` on Android, since Credential
Manager needs it to show the account picker UI.

#### iOS
BeyondLogin does not embed Google's iOS SDK. Instead, implement
[GoogleSignIn-iOS](https://developers.google.com/identity/sign-in/ios) in your own Xcode project and
override `requestGoogleIdToken` on your `ViewService.Listener`:
```
override fun requestGoogleIdToken(callback: (idToken: String?, nonce: String?) -> Unit) {
    // Generate a nonce, run your GoogleSignIn-iOS flow with it, then:
    callback(idToken, nonce) // or callback(null, null) on cancel/failure
}
```
The nonce is an arbitrary string you generate yourself (e.g. a UUID) - pass the same value to both
`GIDSignIn`'s `nonce:` parameter and this callback; Google echoes it back into the ID token's `nonce`
claim unmodified (no hashing needed, unlike Sign in with Apple), and Kratos compares it against what you
send as `idTokenNonce`.

`googleServerClientId` is not used on iOS - your iOS OAuth client is configured directly in the host
app's own Google Sign-In setup, which additionally needs, outside of this library:
* `GIDSignIn.sharedInstance.configuration = GIDConfiguration(clientID: "<ios-client-id>")` set once at
  launch.
* The client ID's **reversed** form (e.g. `com.googleusercontent.apps.xxxxx-yyyy`) registered as a
  `CFBundleURLTypes` URL scheme in `Info.plist`.
* `GIDSignIn.sharedInstance.handle(url)` called from `application(_:open:options:)`, so the sign-in
  redirect can complete.
* The iOS client ID added to Kratos' `additional_id_token_audiences` (see Kratos configuration above),
  since it's a different OAuth client than the Web one used for `client_id`/Android.

## Screenshots
<div style="display: flex; flex-wrap: wrap;">
  <img src="https://github.com/user-attachments/assets/7ab70ebb-6d14-44b9-ae5f-eea892b78b4e" style="width: 40%; max-width: 40%; height: auto; padding: 5px;">
  <img src="https://github.com/user-attachments/assets/6f06a0b8-b8c1-458e-ba5f-9cbb1adf60e3" style="width: 40%; max-width: 40%; height: auto; padding: 5px;">
</div>
<div style="display: flex; flex-wrap: wrap;">
  <img src="https://github.com/user-attachments/assets/963a4b85-56ff-4111-939c-d5e61d71e168" style="width: 40%; max-width: 40%; height: auto; padding: 5px;">
  <img src="https://github.com/user-attachments/assets/88c1620c-d285-4445-94be-69b30c5d608a" style="width: 40%; max-width: 40%; height: auto; padding: 5px;">
</div>
<div style="display: flex; flex-wrap: wrap;">
  <img src="https://github.com/user-attachments/assets/9d9c1365-a000-458e-a9a9-9d361f2fe300" style="width: 40%; max-width: 40%; height: auto; padding: 5px;">
  <img src="https://github.com/user-attachments/assets/701de711-3a41-4418-a239-e07285744021" style="width: 40%; max-width: 40%; height: auto; padding: 5px;">
</div>
<div style="display: flex; flex-wrap: wrap;">
  <img src="https://github.com/user-attachments/assets/062db44d-b53b-45d2-9221-d901cbdf7418" style="width: 40%; max-width: 40%; height: auto; padding: 5px;">
</div>


## License

    Copyright 2026 Misa Munde

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
