package com.abdo.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abdo.chatapp.Constants
import com.abdo.chatapp.R
import com.abdo.chatapp.base.BaseActivity
import com.abdo.chatapp.database.getRooms
import com.abdo.chatapp.databinding.ActivityHomeBinding
import com.abdo.chatapp.model.Room
import com.abdo.chatapp.ui.addRoom.AddRoomActivity
import com.abdo.chatapp.ui.chat.ChatActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(),
    Navigator {
    val adapter = RoomsAdapter(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        initRecyclerView()

    }

    fun initRecyclerView() {
        adapter.onItemClickListener = object : RoomsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, room: Room?) {
                startChatActivity(room)
            }
        }
        viewDataBinding.roomsRecycler.adapter = adapter
    }

    private fun startChatActivity(room: Room?) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(Constants.EXTRA_ROOM, room)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        getRooms(
            onSuccessListener = { querySnapshot ->
                val rooms = querySnapshot.toObjects(Room::class.java)
                adapter.changeData(rooms)

            },
            onFailureListener = {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        )
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun openAddRoomScreen() {
        val intent = Intent(this, AddRoomActivity::class.java)
        startActivity(intent)
    }
}