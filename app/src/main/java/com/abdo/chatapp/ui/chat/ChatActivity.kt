package com.abdo.chatapp.ui.chat

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdo.chatapp.Constants
import com.abdo.chatapp.R
import com.abdo.chatapp.base.BaseActivity
import com.abdo.chatapp.database.getMessagesRef
import com.abdo.chatapp.databinding.ActivityChatBinding
import com.abdo.chatapp.model.Message
import com.abdo.chatapp.model.Room
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

@Suppress("UNUSED_EXPRESSION")
class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>(), Navigator {
    lateinit var room: Room
    val adapter = MessagesAdapter()
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        room = intent.getParcelableExtra(Constants.EXTRA_ROOM)!!
        viewModel.room = room
        viewDataBinding.vm = viewModel
        layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        viewDataBinding.recyclerChat.layoutManager = layoutManager
        viewDataBinding.recyclerChat.adapter = adapter

        listenForMessagesUpdates()
    }

    fun listenForMessagesUpdates() {
        getMessagesRef(room.roomId!!)
            .orderBy("dateTime", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshots, exception ->

                if (exception != null) {

                    Toast.makeText(this, "can't retrieve message", Toast.LENGTH_LONG).show()

                } else {
                    val newMessagesList = mutableListOf<Message>()
                    for (dc in snapshots!!.documentChanges) {
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                val message = dc.document.toObject(Message::class.java)
                                newMessagesList.add(message)
                            }

                            DocumentChange.Type.MODIFIED -> null
                            DocumentChange.Type.REMOVED -> null
                        }
                    }
                    adapter.appendMessages(newMessagesList)
                    viewDataBinding.recyclerChat.smoothScrollToPosition(adapter.itemCount)
                }

            }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun initViewModel(): ChatViewModel {
        return ViewModelProvider(this).get(ChatViewModel::class.java)
    }
}