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
