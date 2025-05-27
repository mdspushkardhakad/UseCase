package com.example.usecase

class SpeedMonitorService( private val notifier: Notifier,
                           private val alertService: AlertService) {
      private val activeRentals = mutableMapOf<String, Rental>()

      fun startRental(rental: Rental) {
          activeRentals[rental.carId] = rental
      }

      fun receiveSpeedUpdate(carId: String, speed: Double) {
          val rental = activeRentals[carId] ?: return

          if (speed > rental.maxSpeed) {
            notifier.notifyCompany(rental, speed)
            alertService.warnUser(rental.userId, speed)
          }
      }
}