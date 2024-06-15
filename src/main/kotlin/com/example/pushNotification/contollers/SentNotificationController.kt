package com.example.pushNotification.contollers

import com.example.pushNotification.requests.NotificationMessageRequest
import com.example.pushNotification.service.FirebaseMessagingService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sent-notifications")
class SentNotificationController(
    private val firebaseMessagingService: FirebaseMessagingService
) {
    @PostMapping
    fun sendSentNotification(@RequestBody notificationMessageRequest: NotificationMessageRequest) :String{
        return firebaseMessagingService.sendNotificationToToken(notificationMessageRequest)
    }
}