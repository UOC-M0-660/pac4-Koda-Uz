package edu.uoc.pac4.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.uoc.pac4.R
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.ui.login.LoginActivity
import edu.uoc.pac4.ui.streams.StreamsActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LaunchActivity : AppCompatActivity() {

    private val authenticationRepository: AuthenticationRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        checkUserSession()
    }

    private fun checkUserSession() {
        lifecycleScope.launch {
            if (authenticationRepository.isUserAvailable()) {
                // User is available, open Streams Activity
                startActivity(Intent(this@LaunchActivity, StreamsActivity::class.java))
            } else {
                // User not available, request Login
                startActivity(Intent(this@LaunchActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}