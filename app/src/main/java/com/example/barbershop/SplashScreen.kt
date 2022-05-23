package com.example.barbershop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.barbershop.login.LoginActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val sharedPreferences=this.getSharedPreferences(Globals.APP_NAME, Context.MODE_PRIVATE)

        var logStatus=sharedPreferences.getBoolean(Globals.LOGGIN_STATUS, false)

        Handler(Looper.getMainLooper()).postDelayed({
            if(logStatus){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
            this.finish()
        },1000)


    }
}