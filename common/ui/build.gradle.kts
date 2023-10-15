plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "org.andiez.common.ui"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":presenter")))
    implementation(project(mapOf("path" to ":common")))

    // Hilt
    implementation(AndroidLib.hilt)
    kapt(AndroidLib.hilt_processor_compiler)

    // Timber
    implementation(AndroidLib.timber)

    // compose
    implementation(AndroidLib.compose_ui)
    implementation(AndroidLib.material_compose)
    implementation(AndroidLib.material3_compose)
    implementation(AndroidLib.rating_bar_compose)
    implementation(AndroidLib.preview_compose)
    implementation(AndroidLib.activity_compose)
    implementation(AndroidLib.navigation)
    implementation(AndroidLib.hilt_navigation_compose)

    // Lottie
    implementation(AndroidLib.lottie)

    // Coil
    implementation(AndroidLib.coil_compose)

}