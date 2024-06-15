package com.example.pushNotification.configurations

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.IOException

@Configuration
@EnableConfigurationProperties(value = [FirebaseProperties::class])
class FirebaseConfig(private val firebaseProperties: FirebaseProperties) {
    @Bean
    fun googleCredentials(): GoogleCredentials? {
        return try {
            if (firebaseProperties.serviceAccount != null) {
                GoogleCredentials.fromStream(ClassPathResource(firebaseProperties.serviceAccount!!).inputStream)
            } else {
                // Use standard credentials chain. Useful when running inside GKE
                GoogleCredentials.getApplicationDefault()
            }
        } catch (e: IOException) {
            return null
        }
    }

    @Bean
    fun firebaseApp(credentials: GoogleCredentials): FirebaseApp {
        val options = FirebaseOptions.builder()
            .setCredentials(credentials)
            .build()

        return FirebaseApp.initializeApp(options)
    }

    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp): FirebaseMessaging {
        return FirebaseMessaging.getInstance(firebaseApp)
    }
}