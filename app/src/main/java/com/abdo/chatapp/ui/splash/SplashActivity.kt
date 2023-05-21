package com.abdo.chatapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.abdo.chatapp.DataUtils
import com.abdo.chatapp.R
import com.abdo.chatapp.database.getUser
import com.abdo.chatapp.model.AppUser
import com.abdo.chatapp.ui.home.HomeActivity
import com.abdo.chatapp.ui.login.LoginActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            checkedInUser()
        }, 3000)
    }

    private fun checkedInUser() {
        val firebaseUser = Firebase.auth.currentUser
        if (firebaseUser == null) {
            startLoginActivity()
        } else {
            getUser(firebaseUser.uid,
                OnSuccessListener { doc ->
                    val user = doc.toObject(AppUser::class.java)
                    DataUtils.user = user
                    startHomeActivity()
                },
                OnFailureListener {
                    startLoginActivity()
                }
            )

        }
    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}