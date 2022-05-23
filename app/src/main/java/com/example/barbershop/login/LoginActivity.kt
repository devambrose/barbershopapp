package com.example.barbershop.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barbershop.R
import com.example.barbershop.changeFragment
import com.example.barbershop.switchFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        (this as AppCompatActivity).supportActionBar?.title="Login Page"

        switchFragment(this.supportFragmentManager,R.id.frameLoginLayout,LoginFragment(),Bundle())

    }
}