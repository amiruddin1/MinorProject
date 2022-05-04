package com.example.shoppingapplication

import android.content.Intent
import android.content.SharedPreferences
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
            if (edtlaaEmail.text.toString().equals("TestAdmin") &&
                ((edtlaaPassword.text.toString().equals("Shivam@0308")) || (edtlaaPassword.text.toString().equals("Aamir@308"))))
            {
                var preference: SharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE)
                var prefeditor = preference.edit()
                prefeditor.putString("UserName", edtlaaEmail.text.toString())
                prefeditor.commit()
                var intent = Intent(this, MainAdminPage::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(this,"Wrong Credential or User Not Exists",Toast.LENGTH_SHORT).show()
            }
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