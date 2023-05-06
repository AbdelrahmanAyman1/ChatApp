package com.abdo.chatapp.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdo.chatapp.base.BaseViewModel
import com.abdo.chatapp.database.addUserToFireStore
import com.abdo.chatapp.model.AppUser
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class RegisterViewModel :BaseViewModel<Navigator>(){
    val firstName=ObservableField<String>()
    val firstNameError=ObservableField<String>()
    val lastName= ObservableField<String>()
    val lastNameError=ObservableField<String>()
    val userName= ObservableField<String>()
    val userNameError=ObservableField<String>()
    val email=ObservableField<String>()
    val emailError=ObservableField<String>()
    val password=ObservableField<String>()
    val passwordError=ObservableField<String>()

//    private lateinit var auth: FirebaseAuth
    val auth = Firebase.auth
    fun createAccount(){

        if (validate()){
            addAccountToFireBase()
        }
    }

    private fun addAccountToFireBase() {
        showLoading.value=true
        auth.createUserWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener {task->
                showLoading.value=false
                if (!task.isSuccessful){
                    messageLiveData.value=task.exception?.localizedMessage
                    Log.e("fireBase","filed"

                    +task.exception?.localizedMessage
                    )
                }else{
//                    messageLiveData.value="Successful registration"
//                    Log.e("fireBase","Success registration")
                    createFirestoreUser(task.result.user?.uid)

                }
            }
    }

    private fun createFirestoreUser(uid: String?) {
        showLoading.value=true
        val user=AppUser(
        id=uid,
            firstName=firstName.get(),
            lastname = lastName.get(),
            userName = userName.get(),
            email = email.get()

        )
        addUserToFireStore(user,
            {
                    showLoading.value=false
                navigator?.openHomeScreen()
            },
            {
                showLoading.value=false
                messageLiveData.value=it.localizedMessage
            }
            )
    }

    fun validate(): Boolean {
       var valid=true
       if (firstName.get().isNullOrBlank()){
           firstNameError.set("please enter first name")
           valid=false
       }else{
           firstNameError.set(null)
       }
       if (lastName.get().isNullOrBlank()){
           lastNameError.set("please enter last name")
           valid=false
       }else{
           lastNameError.set(null)
       }
       if (email.get().isNullOrBlank()){
           emailError.set("please enter email")
           valid=false
       }else{
           emailError.set(null)
       }
       if (userName.get().isNullOrBlank()){
           userNameError.set("please enter user name")
           valid=false
       }else{
           userNameError.set(null)
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