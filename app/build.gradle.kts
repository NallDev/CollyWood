import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.nalldev.collywood"
    compileSdk = 34

    val baseUrl: String = gradleLocalProperties(rootDir).getProperty("BASE_URL") ?: ""
    val apiKey: String = gradleLocalProperties(rootDir).getProperty("API_KEY") ?: ""

    defaultConfig {
        applicationId = "com.nalldev.collywood"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")

    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    implementation("com.airbnb.android:lottie:6.1.0")
    implementation("com.github.zhpanvip:viewpagerindicator:1.2.3")
    implementation("com.github.Dimezis:BlurView:version-2.0.4")
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    implementation("at.wirecube:additive_animations:1.9.3")

    implementation("com.ealva:welite-core:0.5.2-0")
    implementation("com.ealva:welite-javatime:0.5.2-0")
    implementation("com.ealva:welite-ktime:0.5.2-0")
    implementation("com.ealva:ealvalog:0.5.4")
    implementation("com.ealva:ealvalog-core:0.5.4")
    implementation("com.ealva:ealvalog-android:0.5.4")
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("io.insert-koin:koin-android:3.3.2")
    implementation("io.insert-koin:koin-core:3.3.2")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}