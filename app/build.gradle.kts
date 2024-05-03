plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")

}


android {
    namespace = "com.example.last"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.last"
        minSdk = 29
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
}
dependencies {



    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    val rxJavaVersion = "2.1.1"
    val retrofitVersion = "2.11.0"
    val supportVersion = "28.0.0"

    implementation("com.github.delight-im:Android-SimpleLocation:v1.1.0")


    implementation("android.arch.lifecycle:extensions:1.1.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.7.0")


    implementation("androidx.palette:palette:1.0.0")
    implementation("com.android.support:design:$supportVersion")
    implementation("com.github.bumptech.glide:glide:4.16.0")


    implementation("io.reactivex.rxjava2:rxjava:$rxJavaVersion")
    implementation("io.reactivex.rxjava2:rxandroid:$rxJavaVersion")

    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("com.google.android.gms:play-services-location:18.0.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}