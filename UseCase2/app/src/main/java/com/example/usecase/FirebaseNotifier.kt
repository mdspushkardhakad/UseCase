package com.example.usecase

class FirebaseNotifier: Notifier {
    override fun notifyCompany(rental: Rental, currentSpeed: Double) {
        // In real world, you'd call Firebase Messaging API here
        println("Firebase Notification: Car ${rental.carId} exceeded speed at $currentSpeed km/h.")
    }
}
// Notification interface
interface Notifier {
    fun notifyCompany(rental: Rental, currentSpeed: Double)
}