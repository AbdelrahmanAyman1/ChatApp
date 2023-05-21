package com.abdo.chatapp

import com.abdo.chatapp.model.AppUser
import com.google.firebase.firestore.auth.User

object DataUtils {
    var user: AppUser? = null
    var firebaseUser: User? = null
}