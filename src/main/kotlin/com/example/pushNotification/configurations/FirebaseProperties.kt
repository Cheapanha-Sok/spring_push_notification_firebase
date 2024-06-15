package com.example.pushNotification.configurations

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.firebase")
class FirebaseProperties {
    var serviceAccount: String? = null
}