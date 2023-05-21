package com.abdo.chatapp.ui.addRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.abdo.chatapp.R
import com.abdo.chatapp.model.Category

class CategoriesSpinnerAdapter(val items: List<Category>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var myView = view
        var viewHolder: ViewHolder
        if (myView == null) {
            myView = LayoutInflater.from(parent?.context)
                .inflate(
                    R.layout.item_spinner_category,
                    parent, false
                )

            viewHolder = ViewHolder(myView)
            myView.setTag(viewHolder)
        } else {
            viewHolder = myView.tag as ViewHolder
        }
        val item = items[position]

        viewHolder.title.text = item.name
        viewHolder.roomIc.setImageResource(item.imageId!!)

        return myView!!

    }

    class ViewHolder(val view: View) {
        val title: TextView = view.findViewById(R.id.room_title)
        val roomIc: ImageView = view.findViewById(R.id.room_ic)
    }
}