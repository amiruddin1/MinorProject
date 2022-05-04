package com.example.shoppingapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_cart.*

class MainCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cart)

        var sp:SharedPreferences = getSharedPreferences("CartingProduct", MODE_PRIVATE)
        var result = sp.getString("CartProduct","0")
        var qty:String = "0"
        qty = sp.getString("CartQTY","0").toString()
        var arr = ArrayList<Product>()
        var arr_cart = ArrayList<Cart>()
        var db = DBHelper(this)

//        //TODO: Give Provision to Modify This ARR INSTEAD OF ADDING NEW (RESET) EVERY TIME
        arr = db.getProductByItsID(result.toString().toInt())
        Toast.makeText(this,"$result",Toast.LENGTH_LONG).show()
        if(arr.size>0)
        {
            var ct = Cart(arr[0].p_Id,arr[0].p_name,arr[0].p_desc,arr[0].p_price,qty.toInt(),(arr[0].p_price.toInt()*qty.toInt()).toDouble())
            var temp = db.AddProIntoCart(ct)
            if(temp>0)
            {
                Toast.makeText(this,"Product Added To Cart Successfully.",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(this,"Occurs Error While Inserting Product Into Cart.",Toast.LENGTH_LONG).show()
                var intent = Intent(this, MainUserPage::class.java)
                startActivity(intent)
                finish()
            }
        }
        else
        {

        }
        arr_cart = db.getCartedProduct()
        var cAdapt = CartAdapter(this,arr_cart)
        recyclerOPCart.adapter = cAdapt


    }
}