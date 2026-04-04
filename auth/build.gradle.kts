import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "eu.project.auth"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    val googleWebClientId = gradleLocalProperties(rootDir, providers)
        .getProperty("GOOGLE_WEB_CLIENT_ID") ?: "\"unknown google web client id\""

    val supabaseDatabaseUrl = gradleLocalProperties(rootDir, providers)
        .getProperty("SUPABASE_DATABASE_URL") ?: "\"https://example.com\""

    val supabasePublishableKey = gradleLocalProperties(rootDir, providers)
        .getProperty("SUPABASE_PUBLISHABLE_KEY") ?: "\"unknown supabase publishable key\""

    buildTypes {
        debug {
            isMinifyEnabled = false

            buildConfigField("String", "GOOGLE_WEB_CLIENT_ID", googleWebClientId)
            buildConfigField("String", "SUPABASE_DATABASE_URL", supabaseDatabaseUrl)
            buildConfigField("String", "SUPABASE_PUBLISHABLE_KEY", supabasePublishableKey)
        }
        release {
            isMinifyEnabled = false

            buildConfigField("String", "GOOGLE_WEB_CLIENT_ID", googleWebClientId)
            buildConfigField("String", "SUPABASE_DATABASE_URL", supabaseDatabaseUrl)
            buildConfigField("String", "SUPABASE_PUBLISHABLE_KEY", supabasePublishableKey)

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

    // Ktor
    implementation(libs.ktor.client.android)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // Credential Manager
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    // Supabase
    implementation(platform(libs.bom))
    implementation(libs.postgrest.kt)
    implementation(libs.gotrue.kt)

    implementation(project(":common"))
}