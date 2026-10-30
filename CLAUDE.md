# BeyondLogin

Open-source Kotlin Multiplatform library (Compose Multiplatform UI) for Android and iOS that
provides login, registration, password recovery and session handling against an
[Ory Kratos](https://www.ory.sh/kratos/) identity server. Supports email + password, one-time
codes, and native Google SSO via Kratos' OIDC method. Public repo: `climbeyond/beyondlogin`.

## Related repositories

- `../climbeyond-app`: the Climbeyond app, main consumer of this library (has its own
  CLAUDE.md). Check how it uses the library before changing public API.

## Project structure

- Single Gradle module `:shared`, renamed to `beyondlogin` in `settings.gradle.kts` (task paths
  are `:beyondlogin:...`).
- `shared/src/commonMain/kotlin/app/climbeyond/beyondlogin/`
  - `BeyondLogin.kt`: main entry point; `Settings.kt`: configuration; `Session.kt`
  - `ViewService.kt`: talks to Kratos through the generated client
  - `GoogleAuth.kt`: `expect` for native Google sign-in (Credential Manager on Android, iOS actual)
  - `ui/ControllerView.kt`, `ui/screen/` (`LoginView`, `RegisterView`, `RecoveryView`,
    `SessionView`, `SplashView`), `ui/component/`
  - `helpers/` (logger, shared preferences, snackbar, colors), `exception/`
- `shared/src/commonMain/kotlin/org/openapitools/client/`: Kratos API client **generated** by
  `ory/build.sh` from `ory/openapi-<version>.yaml`. Don't hand-edit; regenerate when updating
  the Kratos API version (the script also applies some sed fixes to the output).
- `shared/src/commonMain/composeResources/`: drawables (`beyond_login_*`) and strings.
- `shared/src/androidMain/`, `shared/src/iosMain/`: platform `actual`s.
- README documents the required Kratos configuration and Google SSO setup; keep it in sync when
  changing flows or settings.

## Stack and build

- Kotlin `2.4.10`, Compose Multiplatform, Ktor client, `kotlinx.serialization`,
  `kotlinx-datetime`; Android also uses AndroidX Credentials + Google ID. Versions are in
  `gradle/libs.versions.toml`.
- Targets: Android library, `iosArm64`, `iosSimulatorArm64` (static `BeyondLogin` XCFramework).
- JVM toolchain 21.
- Library version lives in root `build.gradle.kts` (`versionMajor`/`versionMinor`/`versionPatch`
  in `extra`); `versionName` is `major.minor.patch`.
- Published to GitHub Packages (`maven.pkg.github.com/climbeyond/beyondlogin`) by the manually
  triggered `.github/workflows/release.yml`. `githubUser` / `githubToken` are passed as Gradle
  properties; never print or commit credentials.

## Library design rules

- **Public API is used by released apps.** Prefer additive changes; when a breaking change is
  needed, say so explicitly so it can go in a minor/major version and the README can be updated.
- Keep the library generic: no Climbeyond-specific URLs, ids or config values in code or README
  (use placeholders), since other projects can use it with their own Kratos setup.
- Auth code handles credentials and session tokens: never log passwords, codes or tokens.

## Commit messages

Format: `<keyword>: short description` (imperative, lowercase, no period). Add a body below a
blank line only if it needs more explanation.

Core keywords:
- `feat`: new functionality in the public API or UI
- `fix`: bug fix
- `refactor`: code change with no behavior change
- `chore`: deps, build config, tooling, CI
- `docs`: CLAUDE.md, README, comments

Optional, only when it clarifies:
- `ui`: visual/layout-only tweaks (no logic change)
- `deps`: dependency version bumps (subset of `chore`, use if worth calling out separately)

Add a platform scope in parens when a change isn't shared: `fix(ios): ...`, `feat(android): ...`.

Example: `feat: add apple sign-in`
