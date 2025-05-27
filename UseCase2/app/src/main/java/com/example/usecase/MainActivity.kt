package com.example.usecase

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var speedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize TextView reference
        speedTextView = findViewById(R.id.speedTextView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val notifier = FirebaseNotifier()
        val alertService = AlertService()
        val monitorService = SpeedMonitorService(notifier, alertService)

        val rental = Rental(
            rentalId = "r1",
            userId = "uid123",
            carId = "carcitroen",
            maxSpeed = 100.0
        )

        monitorService.startRental(rental)

        // Simulated telematics data updates - update UI with speed
        fun updateSpeedUI(speed: Double) {
            speedTextView.text = "Speed: $speed km/h"
        }

        val speeds = listOf(95.5, 105.5,107.0,205.0)

        for (speed in speeds) {
            updateSpeedUI(speed)
            monitorService.receiveSpeedUpdate("carcitroen", speed)
            // Add delay if simulating real-time updates (optional)
        }
    }
}
