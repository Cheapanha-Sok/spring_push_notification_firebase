package com.example.pushNotification.service

import com.example.pushNotification.requests.NotificationMessageRequest
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class FirebaseMessagingService(
    private val firebaseMessaging: FirebaseMessaging
) {

    fun sendNotificationToToken(notificationMessageRequest: NotificationMessageRequest) :String {
        var notification : Notification = Notification.builder()
                                            .setTitle(notificationMessageRequest.title)
                                            .setBody(notificationMessageRequest.body).build()

        var message : Message = Message.builder()
                                            .setToken(notificationMessageRequest.token)
                                            .setNotification(notification).build()
        try{
            firebaseMessaging.send(message)
            return "Success sending Notification"
        }catch (e : FirebaseMessagingException){
            e.printStackTrace()
            return "Error sending Notification"
        }
    }

}