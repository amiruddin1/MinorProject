package com.example.shoppingapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_my_orders_main.*

class MyOrdersMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders_main)
        var arr = ArrayList<Orders>()

        var preference: SharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE)
        var str = preference.getString("UserName", "tt")

        Toast.makeText(this,"$str",Toast.LENGTH_LONG).show()
        var db = DBHelper(this)
        arr = db.getuserOrders(str)

        var ad = MyOrdersAdapter(this,arr)
        myordersrecycler.adapter = ad
    }
}