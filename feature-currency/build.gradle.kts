@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.plugin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.bank.currency"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    testImplementation (libs.mockito)
    testImplementation (libs.coroutinesTest)

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.materialDesign)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    //Navigation
    implementation(libs.bundles.navigationComponent)
    //Hilt
    implementation(libs.hilt)
    kapt(libs.hiltDaggerCompiler)
    // Arch Components
    implementation(libs.bundles.archComponents)
    // Kotlin Coroutines
    implementation(libs.bundles.kotlinCoroutines)
    implementation(project(":network"))
    implementation(project(":ui"))
    implementation(project(":utils"))

}