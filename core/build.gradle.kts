plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "org.andiez.core"
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_API", "\"https://api.themoviedb.org/3/\"")
            buildConfigField(
                "String",
                "API_KEY",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZmVmOWUwZTZmOTE2NjVlNmI3YTY2NTU4MjU2ZGJiZSIsInN1YiI6IjVlMzQxN2Q0NDMyNTBmMDAxM2JmNjQ0MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rTkzV4llSpSUZrofqE-YW6IytNAIRsnrh7FY4eLAKAU\""
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_API", "\"https://api.themoviedb.org/3/\"")
            buildConfigField(
                "String",
                "API_KEY",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZmVmOWUwZTZmOTE2NjVlNmI3YTY2NTU4MjU2ZGJiZSIsInN1YiI6IjVlMzQxN2Q0NDMyNTBmMDAxM2JmNjQ0MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rTkzV4llSpSUZrofqE-YW6IytNAIRsnrh7FY4eLAKAU\""
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

    // Hilt
    implementation(AndroidLib.hilt)
    kapt(AndroidLib.hilt_processor_compiler)

    // Retrofit
    implementation(AndroidLib.retrofit_android)
    implementation(AndroidLib.gson_converter)
    implementation(AndroidLib.okhttp_logging)

    // Room
    implementation(AndroidLib.room)
    implementation(AndroidLib.room_coroutine)
    kapt(AndroidLib.room_compiler)

    // Timber
    implementation(AndroidLib.timber)

    // Paging
    implementation(AndroidLib.paging)
    implementation(AndroidLib.paging_compose)
    implementation(AndroidLib.room_paging)
}