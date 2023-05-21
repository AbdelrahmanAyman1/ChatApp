package com.abdo.chatapp.ui.home

import com.abdo.chatapp.base.BaseViewModel

class HomeViewModel :BaseViewModel<Navigator>(){
    fun openAddRoom() {
        navigator?.openAddRoomScreen()
    }
}