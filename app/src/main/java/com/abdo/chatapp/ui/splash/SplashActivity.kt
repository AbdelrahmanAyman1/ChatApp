package com.abdo.chatapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.abdo.chatapp.R
import com.abdo.chatapp.ui.login.LoginActivity
import com.abdo.chatapp.ui.register.RegisterActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startLoginActivity()
        },3000)
    }

    private fun startLoginActivity() {
        val intent=Intent(this,  LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}