package com.abdo.chatapp.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdo.chatapp.R
import com.abdo.chatapp.base.BaseActivity as BaseActivity1


abstract class BaseActivity <DB: ViewDataBinding,VM:BaseViewModel<*>>() 
    :AppCompatActivity(){
lateinit var viewModel: VM
lateinit var viewDataBinding: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding= DataBindingUtil.setContentView(this, getLayoutId())
        viewModel=initViewModel()
        subscribeToLiveData()

    }

     fun subscribeToLiveData(){
        viewModel.messageLiveData.observe(this) {message->
            showDialog(message,"ok")
        }
         viewModel.showLoading.observe(this) {show->
            if (show) showLoading()
             else hideLoading()
         }
     }
    var alertDialog:AlertDialog?=null
    fun showDialog(message:String,
                   posActionName:String?=null,
                   posAction:DialogInterface.OnClickListener?=null,
                   negActionName:String?=null,
                   negAction:DialogInterface.OnClickListener?=null,
                   cancelable:Boolean=true
                   ){
        val defaultAction = DialogInterface.OnClickListener { dialog, which -> dialog?.dismiss() }

       val builder=AlertDialog.Builder(this)
           .setMessage(message)
        if (posActionName!=null)
            builder.setPositiveButton(posActionName, posAction?:defaultAction)

            if (negActionName!=null)
            builder.setNegativeButton(negActionName,negAction?:defaultAction)

        builder.setCancelable(cancelable)

     alertDialog =builder.show()

    }
    fun hideAlertDialog(){
        alertDialog?.dismiss()
        alertDialog=null
    }
    var progressDialog:ProgressDialog?=null

    constructor(parcel: Parcel) : this() {

    }

    fun showLoading(){
        progressDialog=ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }
    fun hideLoading(){
        progressDialog?.dismiss()
        progressDialog=null
    }
    abstract fun getLayoutId():Int
    abstract fun initViewModel():VM



}