plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)

    alias(libs.plugins.serialization).apply(false)
    alias(libs.plugins.jetbrainsCompose).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
}

extra.apply {
    set("androidMinSdk", 26)
    set("androidTargetSdk", 35)
    set("androidCompileSdk", 35)
    set("versionMajor", 0)
    set("versionMinor", 3)
    set("versionPatch", 2)
    set("versionCode",
            ext.get("androidMinSdk") as Int * 10000000
                    + ext.get("versionMajor") as Int * 10000
                    + ext.get("versionMinor") as Int * 100
                    + ext.get("versionPatch") as Int)
    set("versionName",
            "${ext.get("versionMajor")}.${ext.get("versionMinor")}.${ext.get("versionPatch")}")
}