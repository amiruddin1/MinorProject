package com.example.shoppingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_create_account.*

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
//                var users = user(edtCAEmail.text.toString(),edtCAFName.text.toString(),edtCAEPassword.text.toString(),edtCAMobileNumber.text.toString())
//                var db = DBHelper(this)
//                var flag = db.insertUser(users)
//                if(flag>0)
//                {
//                    Toast.makeText(this,"Your Details Submit Successfully.",Toast.LENGTH_LONG).show()
//                    var intent = Intent(this,MainLoginPage::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                else
//                {
//                    Toast.makeText(this,"Email Already Regestered with Diffrent Account!",Toast.LENGTH_LONG).show()
//                    var intent = Intent(this,MainLoginPage::class.java)
//                    startActivity(intent)
//                    finish()
                //}
            }
        }
    }
}