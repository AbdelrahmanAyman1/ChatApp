package com.abdo.chatapp.ui.addRoom

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.abdo.chatapp.R
import com.abdo.chatapp.base.BaseActivity
import com.abdo.chatapp.databinding.ActivityAddRoomBinding

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding,
        AddRoomViewModel>(), Navigator {
    lateinit var adapter: CategoriesSpinnerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        adapter = CategoriesSpinnerAdapter(viewModel.categories)
        viewDataBinding.spinner.adapter = adapter
        viewDataBinding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.selectedCategory = viewModel.categories[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        viewModel.roomAdded.observe(
            this
        ) { added ->

            if (added) {
                showDialog(
                    "Room Added Successfully",
                    posActionName = "ok",
                    posAction = DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()

                        finish()

                    },
                    cancelable = false
                )


            }

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }


}