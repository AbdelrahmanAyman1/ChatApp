package com.abdo.chatapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abdo.chatapp.R
import com.abdo.chatapp.base.BaseActivity
import com.abdo.chatapp.databinding.ActivityLoginBinding
import com.abdo.chatapp.ui.home.HomeActivity
import com.abdo.chatapp.ui.register.RegisterActivity
import com.abdo.chatapp.ui.register.RegisterViewModel

class LoginActivity:BaseActivity<ActivityLoginBinding,LoginViewModel> (),Navigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        viewModel.navigator=this
    }
    override fun getLayoutId(): Int {
     return   R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }
    override fun openHomeScreen(){
    val intent=Intent(this,HomeActivity::class.java)
    startActivity(intent)
    }

    override fun openRegisterScreen() {
        val intent=Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}