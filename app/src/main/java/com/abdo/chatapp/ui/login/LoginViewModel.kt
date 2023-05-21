package com.abdo.chatapp.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import com.abdo.chatapp.DataUtils
import com.abdo.chatapp.base.BaseViewModel
import com.abdo.chatapp.database.signIn
import com.abdo.chatapp.model.AppUser
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:BaseViewModel<Navigator>() {
    val email= ObservableField<String>()
    val emailError= ObservableField<String>()
    val password= ObservableField<String>()
    val passwordError= ObservableField<String>()
    val fireBase=Firebase.auth
    fun login(){
        if (validate()){
            loginWithFireBase()
        }
    }
    fun openRegister(){
        navigator?.openRegisterScreen()
    }
    private fun loginWithFireBase() {
        showLoading.value=true
        fireBase.signInWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener {task->
                showLoading.value = false
                if (!task.isSuccessful) {
                    messageLiveData.value =
                        task.exception?.localizedMessage
                    Log.e(
                        "fireBase", "filed"

                                + task.exception?.localizedMessage
                    )
                } else {
                    // navigator?.openHomeScreen()
                    checkUserFromFireStore(task.result.user?.uid)
                }
            }
    }

    private fun checkUserFromFireStore(uid:String?) {
        showLoading.value = true
        signIn(uid!!, OnSuccessListener { docSnapshot ->
            showLoading.value = false
            if (docSnapshot.exists()) {
                val user = docSnapshot.toObject(AppUser::class.java)
                if (user != null) {
                    DataUtils.user = user
                    navigator?.openHomeScreen()
                    return@OnSuccessListener
                }
            }
            messageLiveData.value = "Invalid email or password"
        }) {
            showLoading.value = false
            messageLiveData.value = it.localizedMessage

        }
    }

    fun validate(): Boolean {
        var valid=true

        if (email.get().isNullOrBlank()){
            emailError.set("please enter email")
            valid=false
        }else{
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()){
            passwordError.set("please enter password")
            valid=false
        }else{
            passwordError.set(null)
        }
        return valid
    }

}