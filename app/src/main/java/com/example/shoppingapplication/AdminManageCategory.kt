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
import kotlinx.android.synthetic.main.activity_admin_manage_category.*
import kotlinx.android.synthetic.main.dialogue_addcategory.*
import kotlinx.android.synthetic.main.dialogue_addproduct.*

class AdminManageCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_manage_category)
        refreshList()
        btnmanageCategoryAddCategory.setOnClickListener {
            var dialog_category = Dialog(this)
            dialog_category.setContentView(R.layout.dialogue_addcategory)
            dialog_category.setCancelable(false)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog_category.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.gravity = Gravity.CENTER

            dialog_category.window!!.attributes = lp
            dialog_category.show()

            dialog_category.btnclosecategoryDialogue.setOnClickListener {
                dialog_category.dismiss()
            }
            dialog_category.btnAddcategoryfinally.setOnClickListener {
                if(dialog_category.edtcategorynameDialogue.text.toString().equals(""))
                {
                    Toast.makeText(this,"Fill All The Data Accurately", Toast.LENGTH_LONG).show()
                }
                else
                {
                    var ct = Category(dialog_category.edtcategorynameDialogue.text.toString())
                    var db = DBHelper(this)
                    var result = db.AddCategory(ct)

                    if(result>0)
                    {
                        Toast.makeText(this,"Category Inserted Successfully.", Toast.LENGTH_LONG).show()
                        dialog_category.dismiss()
                        refreshList()
                        var intent = Intent(this,MainAdminPage::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this,"Having Problems...", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
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
    fun refreshList()
    {
        var db = DBHelper(this)
        var arr = db.getAllTheCategories()

        var ad = ManageCategoryRCVAdapter(this,arr)
        RCVManageCategory.adapter = ad
    }
}
