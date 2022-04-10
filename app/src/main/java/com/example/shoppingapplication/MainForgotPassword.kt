package com.example.shoppingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_forgot_password.*

class MainForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_forgot_password)
        txtfpBacktoLogin.setOnClickListener {
            var intent = Intent(this,MainLoginPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}