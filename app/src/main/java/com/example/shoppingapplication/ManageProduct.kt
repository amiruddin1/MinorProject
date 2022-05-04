package com.example.shoppingapplication

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_manage_product.*
import kotlinx.android.synthetic.main.dialogue_addproduct.*

class ManageProduct : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var categories = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        var flag:Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_product)

        categories.add("Select Category")

        var dbt = DBHelper(this)
        for (i in 0 .. dbt.getAllCategories().size-1){
            categories.add(dbt.getAllCategories()[i].toString())
        }

        btnAddNewProductAdmin.setOnClickListener {

            var dialog = Dialog(this)
            dialog.setContentView(R.layout.dialogue_addproduct)
            dialog.setCancelable(false)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.gravity = Gravity.CENTER

            dialog.window!!.attributes = lp
            dialog.show()

            dialog.spinner.onItemSelectedListener = this
            val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(this,android.R.layout.simple_spinner_item,
                categories as List<Any?>
            )
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dialog.spinner.adapter = ad

            dialog.btnAddproductfinally.setOnClickListener {
                if(dialog.edtproductnameDialogue.text.toString().equals("")||dialog.edtproductdescDialogue.text.toString().equals("")
                    ||dialog.edtproductpriceDialogue.text.toString().equals("")||dialog.edtproductquantityDialogue.text.toString().equals("")
                    )
                {
                    Toast.makeText(this,"Fill All The Data Accurately",Toast.LENGTH_LONG).show()
                }
                else if(dialog.spinner.selectedItem.toString().equals("Select Category"))
                {
                    Toast.makeText(this,"Choose The Appropriate Category",Toast.LENGTH_LONG).show()
                }
                else
                {
                    var db = DBHelper(this)
                    var id:Int = db.cnameToCId(dialog.spinner.selectedItem.toString())
                    var arr = Product(id,dialog.edtproductnameDialogue.text.toString(),dialog.edtproductdescDialogue.text.toString(),dialog.edtproductpriceDialogue.text.toString().toDouble(),dialog.edtproductquantityDialogue.text.toString().toInt())
                    var result = db.AddProduct(arr)
                    if(result>0)
                    {
                        dialog.dismiss()
                        Toast.makeText(this,"Product Added Successfully",Toast.LENGTH_LONG).show()
                        var intent = Intent(this,MainAdminPage::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            dialog.btncloseDialogue.setOnClickListener{
                dialog.dismiss()
            }
        }
        var db = DBHelper(this)
        var arr = db.RetrieveAllProduct()
        var adapter=MyProductAdapter(this,arr)
        productrecyclerview.adapter=adapter
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var item1 = item.itemId

        if(item1.equals(R.id.menuLogout))
        {
            var preference: SharedPreferences =getSharedPreferences("mypref", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.clear()
            prefeditor.commit()
            var intent = Intent(this,MainLoginPage::class.java)
            startActivity(intent)
            finish()
        }
        else if(item1.equals(R.id.menuShoppingCart))
        {
            var intent = Intent(this,MainCart::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(applicationContext,
            categories[p2],
            Toast.LENGTH_LONG)
            .show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}