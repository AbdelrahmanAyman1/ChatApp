package com.abdo.chatapp.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abdo.chatapp.R
import com.abdo.chatapp.base.BaseActivity
import com.abdo.chatapp.databinding.ActivityRegisterBinding
import com.abdo.chatapp.ui.home.HomeActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewModel>(),Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        viewModel.navigator=this

    }
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun openHomeScreen() {
        val intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}