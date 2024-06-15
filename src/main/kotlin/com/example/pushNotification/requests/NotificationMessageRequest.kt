package com.example.pushNotification.requests

data class NotificationMessageRequest(
    var title : String,
    var body :String,
    var topic : String,
    var token : String,
)