package com.example.usecase

class AlertService {
    fun warnUser(userId: String, speed: Double) {
        // Simulate sending warning to user's device
        println("Warning sent to user $userId: You are driving at $speed km/h, slow down!")
    }
}