plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "org.andiez.submissioncompose"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        applicationId = "org.andiez.submissioncompose"
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            applicationIdSuffix = ".debug"
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            manifestPlaceholders["enableCrashReporting"] = true
        }

        debug {
            isShrinkResources = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            manifestPlaceholders["enableCrashReporting"] = false
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":common:ui")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":feature:movie")))
    implementation(project(mapOf("path" to ":feature:tvshow")))
    implementation(project(mapOf("path" to ":feature:favorite")))

    implementation(AndroidLib.kotlin_stdlib)
    implementation(AndroidLib.androidx_appcompat)

    // Testing
    testImplementation(AndroidTestLib.junit_lib)
    androidTestImplementation(AndroidTestLib.android_test_junit)
    androidTestImplementation(AndroidTestLib.android_test_espresso_core)
    androidTestImplementation(AndroidTestLib.test_core)
    testImplementation(AndroidTestLib.mockito)
    testImplementation(AndroidTestLib.core_testing)
    testImplementation(AndroidTestLib.coroutine)
    androidTestImplementation(AndroidTestLib.junit_compose)
    debugImplementation(AndroidTestLib.compose_tooling)
    debugImplementation(AndroidTestLib.compose_test)

    // ViewModel
    implementation(AndroidLib.viewmodel_ktx)
    implementation(AndroidLib.viewmodel_runtime)
    implementation(AndroidLib.viewmodel_extension)
    implementation(AndroidLib.viewmodel_compose)
    kapt(AndroidLib.viewmodel_compiler)

    // Timber
    implementation(AndroidLib.timber)

    // Hilt
    implementation(AndroidLib.hilt)
    kapt(AndroidLib.hilt_processor_compiler)

    // Multidex
    implementation(AndroidLib.multidex)

    // compose
    implementation(AndroidLib.compose_ui)
    implementation(AndroidLib.material_compose)
    implementation(AndroidLib.material3_compose)
    implementation(AndroidLib.preview_compose)
    implementation(AndroidLib.activity_compose)
    implementation(AndroidLib.navigation)
    implementation(AndroidLib.hilt_navigation_compose)

    // Lottie
    implementation(AndroidLib.lottie)

    // Coil
    implementation(AndroidLib.coil_compose)

    // Paging
    implementation(AndroidLib.paging)
    implementation(AndroidLib.paging_compose)
}