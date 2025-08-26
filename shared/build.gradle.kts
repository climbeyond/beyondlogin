import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import java.text.SimpleDateFormat
import java.util.Date

plugins {
    `maven-publish`

    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    alias(libs.plugins.serialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(21)

    androidTarget {
        publishLibraryVariants("release")
        publishLibraryVariantsGroupedByFlavor = true
    }

    val xcf = XCFramework("BeyondLogin")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "BeyondLogin"
            xcf.add(this)
            isStatic = true
            binaryOption("bundleId","app.climbeyond.beyondlogin.ios")
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
                optIn("kotlin.time.ExperimentalTime")
            }
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlin.serialization)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        androidMain.dependencies {
            implementation(libs.androidx.preference.ktx)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }

    targets.all {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}

publishing {
    val GITHUB_USER: String? by project
    val GITHUB_TOKEN: String? by project

    repositories {
        version = rootProject.ext.get("versionName") as String
        maven {
            setUrl("https://maven.pkg.github.com/climbeyond/beyondlogin")
            credentials {
                username = GITHUB_USER
                password = GITHUB_TOKEN
            }
        }
    }
}

android {
    namespace = "app.climbeyond.beyondlogin"
    compileSdk = rootProject.ext.get("androidCompileSdk") as Int

    val versionName = rootProject.ext.get("versionName") as String

    androidComponents.beforeVariants {
        it.enable = run {
            it.buildType != "debug"
        }
    }

    defaultConfig {
        minSdk = rootProject.extra.get("androidMinSdk") as Int

        buildConfigField("String", "BUILD_TIME", "\"${SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                .format(Date())}\"")
        buildConfigField("String", "VERSION_NAME", "\"${versionName}\"")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes += "/values/strings.xml"
            excludes += "/drawable/*"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

task("testClasses").doLast {
    println("This is a dummy testClasses task")
}

afterEvaluate {
    tasks.getByName("testClasses") {
        onlyIf { return@onlyIf false }
    }
    tasks.getByName("linkDebugFrameworkIosX64") {
        onlyIf { return@onlyIf false }
    }
    tasks.getByName("linkDebugFrameworkIosArm64") {
        onlyIf { return@onlyIf false }
    }
    tasks.getByName("linkDebugFrameworkIosSimulatorArm64") {
        onlyIf { return@onlyIf false }
    }
}

tasks.create<Copy>("publish-android") {
    val androidName = "beyondlogin-${rootProject.extra.get("versionName") as String}.aar"
    val apkDir = file("${project.rootDir.absolutePath}/shared/build/outputs/aar/beyondlogin-release.aar")
    val outDir = file("${project.rootDir.absolutePath}/aar")

    from(apkDir)
    into(outDir)
    include("**/*")
    rename("beyondlogin-release.aar", androidName)
    doLast {
        println(">>>publish $androidName success!" +
                "\nfrom: $apkDir" +
                "\ninto: $outDir")
    }
}

tasks.create<Copy>("publish-ios") {
    // iOS xcFramework copy
    val xcSaveDir = "beyondlogin-${rootProject.extra.get("versionName") as String}.xcframework"
    val xcDir = file("${project.rootDir.absolutePath}/shared/build/XCFrameworks/release/BeyondLogin.xcframework")
    val outXcDir = file("${project.rootDir.absolutePath}/xcframework/${xcSaveDir}")

    from(xcDir)
    into(outXcDir)
    include("**/*")
    doLast {
        println(">>>publish $xcSaveDir success!" +
                "\nfrom: $xcDir" +
                "\ninto: $outXcDir")
    }
}
