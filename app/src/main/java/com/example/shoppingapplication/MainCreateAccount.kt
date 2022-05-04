package com.example.shoppingapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_create_account.*
import kotlinx.android.synthetic.main.activity_main_login_as_admin.*

class MainCreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_create_account)
        txtCAAlreadyHaveAccount.setOnClickListener {
            var intent = Intent(this,MainLoginPage::class.java)
            startActivity(intent)
            finish()
        }
        btnCACreateAccount.setOnClickListener {
            if(edtCAEmail.text.toString().equals("") || edtCAFName.text.toString().equals("") ||
                edtCAEPassword.text.toString().equals("") || edtCACPassword.text.toString().equals("") || edtCAMobileNumber.text.toString().equals(""))
            {
                Toast.makeText(this,"All Fields Mandotary to fill",Toast.LENGTH_LONG).show()
            }
            else if(edtCAEPassword.text.toString() != edtCACPassword.text.toString())
            {
                Toast.makeText(this,"Password & Confirm Password Should Same!!",Toast.LENGTH_LONG).show()
            }
            else if(edtCAEPassword.text.toString().length < 8)
            {
                Toast.makeText(this,"Password Length Should 8 or More Than That!",Toast.LENGTH_LONG).show()
            }
            else
            {
                var uss = user(edtCAEmail.text.toString(),edtCAFName.text.toString(),edtCAEPassword.text.toString(),edtCAMobileNumber.text.toString())
                var db = DBHelper(this)
                var flag = db.insertUser(uss)

                if(flag>0)
                {
                    Toast.makeText(this,"Account Created Successfully.",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, MainLoginPage::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Some Problems Ocuurs while Inserting",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}