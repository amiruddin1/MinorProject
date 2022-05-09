package com.example.shoppingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_product_stock_management.*

class List_Product_StockManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product_stock_management)
        var db = DBHelper(this)
        var arr = db.RetrieveAllProduct()

        var adp = List_Product_Stock_Adapter(this,arr)
        STOCK_RecycleOP.adapter = adp
    }
}