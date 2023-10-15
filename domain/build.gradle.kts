plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "org.andiez.domain"
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
}

dependencies {
    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":data:movie")))
    implementation(project(mapOf("path" to ":data:tvshow")))
    implementation(project(mapOf("path" to ":data:favorite")))
    implementation(project(mapOf("path" to ":data:detail")))

    // Hilt
    implementation(AndroidLib.hilt)
    kapt(AndroidLib.hilt_processor_compiler)

    // Timber
    implementation(AndroidLib.timber)

    // Paging
    implementation(AndroidLib.paging)
    implementation(AndroidLib.paging_compose)
}