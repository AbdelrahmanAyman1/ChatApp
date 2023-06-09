package com.abdo.chatapp.database

import com.abdo.chatapp.model.AppUser
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun getCollection(collectionName:String):CollectionReference{
    val db=Firebase.firestore
   return db.collection(AppUser.COLLECTION_NAME)

}
fun addUserToFireStore(user:AppUser,onSuccessListener: OnSuccessListener<Void>,
onFailureListener: OnFailureListener){
    val userCollection= getCollection(AppUser.COLLECTION_NAME)
   val userDoc= userCollection.document(user.id!!)
    userDoc.set(user)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}
fun sinIn(uid:String,onSuccessListener: OnSuccessListener<DocumentSnapshot>,onFailureListener: OnFailureListener){

    val collectionRef= getCollection(AppUser.COLLECTION_NAME)
    collectionRef.document(uid)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}