package com.example.decathlon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/** Splash activity - first activity to show the welcome text. */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Executors.newSingleThreadScheduledExecutor().schedule({
            startActivity(
                Intent(
                    /* packageContext= */ this@SplashActivity,
                    /* class= */ ProductActivity::class.java
                )
            )
        }, SPLASH_DURATION, TimeUnit.SECONDS)
    }

    private companion object {
        const val SPLASH_DURATION = 1L
    }
}
