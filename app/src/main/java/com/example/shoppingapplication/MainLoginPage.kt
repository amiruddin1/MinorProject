package com.example.shoppingapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_login_as_admin.*
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
            if(edtlpEmail.text.toString().equals("")|| edtlpPassword.text.toString().equals(""))
            {
                Toast.makeText(this,"All Field Must Not be Empty",Toast.LENGTH_SHORT).show()
            }
            else
            {
                var db = DBHelper(this)
                var arr:ArrayList<user> = db.getUser(edtlpEmail.text.toString())
                if(arr.size<1)
                {
                    Toast.makeText(this,"User Not Exists!",Toast.LENGTH_SHORT).show()
                }
                else if(arr[0].email.equals(edtlpEmail.text.toString(),true) && arr[0].password.equals(edtlpPassword.text.toString()))
                {
                    var preference: SharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE)
                    var prefeditor = preference.edit()
                    prefeditor.putString("UserName", arr[0].name.toString())
                    prefeditor.commit()

                    var intent = Intent(this,MainUserPage::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show()
                }
            }
        }
        txtlploginasAdmin.setOnClickListener {
            var intent = Intent(this, MainLoginAsAdmin::class.java)
            startActivity(intent)
            finish()
        }
    }
}