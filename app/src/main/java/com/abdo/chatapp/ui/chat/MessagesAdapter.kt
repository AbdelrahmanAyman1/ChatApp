package com.abdo.chatapp.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdo.chatapp.DataUtils
import com.abdo.chatapp.R
import com.abdo.chatapp.databinding.ItemMessageReceiveBinding
import com.abdo.chatapp.databinding.ItemMessageSentBinding
import com.abdo.chatapp.model.Message

class MessagesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = mutableListOf<Message?>()

    class SentMessageViewHolder(val viewDataBinding: ItemMessageSentBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(message: Message?) {
            viewDataBinding.item = message
            viewDataBinding.executePendingBindings()
        }

    }

    class ReceivedMessageViewHolder(val viewDataBinding: ItemMessageReceiveBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(message: Message?) {
            viewDataBinding.itemMessage = message
            viewDataBinding.executePendingBindings()

        }

    }

    val RICEIVED = 1
    val SENT = 2
    override fun getItemViewType(position: Int): Int {
        val message = items.get(position)
        if (message?.senderId == DataUtils.user?.id) {
            return SENT
        }
        return RICEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (RICEIVED == viewType) {
            val itemBinding: ItemMessageReceiveBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_message_receive,
                    parent,
                    false
                )
            return ReceivedMessageViewHolder(itemBinding)
        }
        val itemBinding: ItemMessageSentBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_message_sent,
                parent,
                false
            )
        return SentMessageViewHolder(itemBinding)

    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is SentMessageViewHolder) {
            holder.bind(items.get(position))

        } else if (holder is ReceivedMessageViewHolder) {
            holder.bind(items.get(position))
        }

    }

    fun appendMessages(newMessagesList: MutableList<Message>) {
        items.addAll(newMessagesList)
        notifyItemRangeInserted(items.size + 1, newMessagesList.size)
    }


}