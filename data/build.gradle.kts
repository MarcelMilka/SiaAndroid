import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "eu.project.data"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false

            val apiBaseUrlDebug = gradleLocalProperties(rootDir, providers)
                .getProperty("API_BASE_URL_DEBUG") ?: "\"http://example.com\""

            buildConfigField("String", "API_BASE_URL", apiBaseUrlDebug)
        }
        release {
            isMinifyEnabled = false

            val apiBaseUrlRelease = gradleLocalProperties(rootDir, providers)
                .getProperty("API_BASE_URL_RELEASE") ?: "\"https://example.com\""

            buildConfigField("String", "API_BASE_URL", apiBaseUrlRelease)

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //  Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.retrofit)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    testImplementation(libs.mockwebserver)

    // Gson converter
    implementation(libs.converter.gson)

    // MockK
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)

    // Coroutines test
    testImplementation(libs.kotlinx.coroutines.test)

    implementation(project(":common"))
    implementation(project(":auth"))
}