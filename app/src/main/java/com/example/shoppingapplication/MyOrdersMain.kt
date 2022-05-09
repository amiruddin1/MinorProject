package com.example.shoppingapplication

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_my_orders_main.*
import kotlinx.android.synthetic.main.myaccountdetaildialog.*

class MyOrdersMain : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders_main)
        var arr = ArrayList<Orders>()

        var preference: SharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE)
        var str = preference.getString("UserName", "tt")

        var db = DBHelper(this)
        arr = db.getuserOrders(str)

        var ad = MyOrdersAdapter(this,arr)
        myordersrecycler.adapter = ad
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var item1 = item.itemId

        if(item1.equals(R.id.menuLogout))
        {
            var preference:SharedPreferences=getSharedPreferences("mypref", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.clear()
            prefeditor.commit()
            var intent = Intent(this,MainLoginPage::class.java)
            startActivity(intent)
            finish()
        }
        else if(item1.equals(R.id.menuShoppingCart))
        {
            var sp :SharedPreferences = getSharedPreferences("CartingProduct",AppCompatActivity.MODE_PRIVATE)
            var prefEditor = sp.edit()
            prefEditor.clear()
            prefEditor.commit()

            var intent = Intent(this,MainCart::class.java)
            startActivity(intent)
        }
        else if(item1.equals(R.id.menuMyOrders))
        {
            var intent = Intent(this,MyOrdersMain::class.java)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)
    }
}