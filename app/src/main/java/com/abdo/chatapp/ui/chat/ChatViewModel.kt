package com.abdo.chatapp.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.abdo.chatapp.DataUtils
import com.abdo.chatapp.base.BaseViewModel
import com.abdo.chatapp.database.addMessage
import com.abdo.chatapp.model.Message
import com.abdo.chatapp.model.Room
import java.util.Date

class ChatViewModel : BaseViewModel<Navigator>() {
    val messageField = ObservableField<String>()
    val toastLiveData = MutableLiveData<String>()
    var room: Room? = null
    fun sendMessage() {
        val message = Message(
            content = messageField.get(),
            roomId = room?.roomId,
            senderId = DataUtils.user?.id,
            senderName = DataUtils.user?.userName,
            dateTime = Date().time

        )
        addMessage(message,
            {
                messageField.set("")
            }, {
                toastLiveData.value = "Something went wrong , try again later"
            }
        )
    }
}