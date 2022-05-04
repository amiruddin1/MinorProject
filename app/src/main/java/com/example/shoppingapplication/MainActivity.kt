package com.example.shoppingapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            var preference: SharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE)
            var str = preference.getString("UserName","tt")
            if(str=="tt") {
                var intent = Intent(this, MainLoginPage::class.java)
                startActivity(intent)
                finish()
            }
            else if(str == "TestAdmin"){
                var intent = Intent(this, MainAdminPage::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                var intent = Intent(this, MainUserPage::class.java)
                startActivity(intent)
                finish()
            }
        },3000)
    }
}