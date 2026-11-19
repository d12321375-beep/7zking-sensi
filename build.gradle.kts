plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.zking.sensi"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.zking.sensi"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
}
