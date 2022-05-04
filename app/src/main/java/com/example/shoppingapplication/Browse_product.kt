package com.example.shoppingapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_browse_product.*

class Browse_product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_product)
        var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
        var value = preference.getString("UserName","tt")

        var db = DBHelper(this)
        var arr = db.getProductsforbrose(value)

        var bp = broseproductAdapter(this,arr)
        myBrowseProductRecycler.adapter = bp


    }
}