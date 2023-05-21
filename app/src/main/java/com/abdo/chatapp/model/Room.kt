package com.abdo.chatapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var roomId: String? = null,
    val name: String? = null,
    val desc: String? = null,
    val categoryId: String? = null,
) : Parcelable {
    fun categoryImageId(): Int? {
        return Category.fromId(categoryId ?: Category.MOVIES).imageId

    }

    companion object {
        const val COLLECTION_NAME = "Rooms"
    }
}
