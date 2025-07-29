plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.notification"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.notification"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("androidx.room:room-runtime:2.7.2")
    annotationProcessor ("androidx.room:room-compiler:2.7.2")
    implementation ("androidx.recyclerview:recyclerview:1.4.0")
    // SweetToast from JitPack
    implementation("com.github.TheHasnatBD:SweetToast:1.0.2")

    // SweetAlertDialog library
    implementation("com.github.f0ris.sweetalert:library:1.6.2")

}