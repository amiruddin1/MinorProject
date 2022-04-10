package com.example.shoppingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_login_page.*

class MainLoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login_page)
        txtlpForgotPassword.setOnClickListener {
            var intent = Intent(this,MainForgotPassword::class.java)
            startActivity(intent)
        }
        txtlpCreateAccount.setOnClickListener {
            var intent = Intent(this,MainCreateAccount::class.java)
            startActivity(intent)
            finish()
        }
        btnlpLogin.setOnClickListener {
            var intent = Intent(this,MainUserPage::class.java)
            startActivity(intent)
            finish()
        }
        txtlploginasAdmin.setOnClickListener {
            var intent = Intent(this,MainLoginAsAdmin::class.java)
            startActivity(intent)
            finish()
        }
    }
}