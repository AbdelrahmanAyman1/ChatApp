package com.abdo.chatapp.ui.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.abdo.chatapp.base.BaseViewModel
import com.abdo.chatapp.database.addRoom
import com.abdo.chatapp.model.Category
import com.abdo.chatapp.model.Room

class AddRoomViewModel : BaseViewModel<Navigator>() {
    val roomName = ObservableField<String>()
    val desc = ObservableField<String>()
    val roomNameError = ObservableField<String>()
    val descError = ObservableField<String>()
    val categories = Category.getCategoriesList()
    var selectedCategory = categories[0]
    val roomAdded = MutableLiveData<Boolean>()

    fun createRoom() {
        if (!validate()) {
            return
        } else {
            val room = Room(
                name = roomName.get(),
                desc = desc.get(),
                categoryId = selectedCategory.id
            )

            showLoading.value = true
            addRoom(
                room,
                onSuccessListener = {
                    showLoading.value = false
                    roomAdded.value = true
                },
                onFailureListener = {
                    showLoading.value = false
                    messageLiveData.value = it.localizedMessage
                }
            )
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (roomName.get().isNullOrBlank()) {
            roomNameError.set("Please Enter Room Name...")
            isValid = false
        } else {
            roomNameError.set(null)
        }
        if (desc.get().isNullOrBlank()) {
            descError.set("Please Enter Room Description...")
            isValid = false
        } else {
            descError.set(null)
        }
        return isValid

    }
}