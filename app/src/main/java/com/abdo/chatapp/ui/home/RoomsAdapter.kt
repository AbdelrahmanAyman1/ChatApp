package com.abdo.chatapp.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdo.chatapp.R
import com.abdo.chatapp.databinding.RoomItemBinding
import com.abdo.chatapp.model.Room

class RoomsAdapter(var items: List<Room?>?) :
    RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val viewDataBinding: RoomItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.room_item, parent, false
            )
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items!![position])
        onItemClickListener.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, items!![position]!!)
            }
        }
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, room: Room?) {

        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun changeData(rooms: List<Room>) {
        items = rooms
        notifyDataSetChanged()

    }

    class ViewHolder(val viewDataBinding: RoomItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(room: Room?) {
            viewDataBinding.item = room
            viewDataBinding.invalidateAll()
        }

    }
}
//////////////////////////////////////////////////////////////