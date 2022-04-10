package com.example.shoppingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_login_as_admin.*
import kotlinx.android.synthetic.main.activity_main_login_page.*

class MainLoginAsAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login_as_admin)
        btnlaaLogin.setOnClickListener {
            Toast.makeText(this, "Valid User", Toast.LENGTH_LONG).show()
        }
        txtlaaCreateAccount.setOnClickListener {
            Toast.makeText(this, "Contact Authority", Toast.LENGTH_LONG).show()
        }
        txtlaaForgotPassword.setOnClickListener {
            Toast.makeText(this, "Contact Authority", Toast.LENGTH_LONG).show()
        }
        txtlaaloginasUser.setOnClickListener {
            var intent = Intent(this,MainLoginPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}