package com.example.shoppingapplication

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_admin_page.*
import kotlinx.android.synthetic.main.reports_management_dialog.*
import java.text.NumberFormat
import java.util.*

class MainAdminPage : AppCompatActivity() {
    // PRODUCT STOCK MANAGEMENT SHOULD BE ADDED...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin_page)
        var preference: SharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE)
        var str = preference.getString("UserName","tt")

        var db = DBHelper(this)
        var arrrr = db.GetNoofThings()
        txtAdminNoUser.setText("${arrrr[2].toString().toInt()}")
        txtAdminNoCategory.setText("${arrrr[1].toString().toInt()}")
        txtAdminNoProduct.setText("${arrrr[0].toString().toInt()}")

        btnAdminProductManage.setOnClickListener {
            var intent = Intent(this,ManageProduct::class.java)
            startActivity(intent)
        }
        btnAdminMoreReports.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.reports_management_dialog)
            dialog.setCancelable(false)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.gravity = Gravity.CENTER

            dialog.window!!.attributes = lp
            dialog.show()
            var db = DBHelper(this)
            var result = db.GetTotalSale()
            val format: NumberFormat = NumberFormat.getCurrencyInstance()
            format.setMaximumFractionDigits(0)
            dialog.txtTotalSaleReports.setText(format.format(result))

            dialog.btnDoneFromReportsDialog.setOnClickListener {
                dialog.dismiss()
            }
        }
        btnAdmiManageUser.setOnClickListener {
            var intent = Intent(this,AdminManageUser::class.java)
            startActivity(intent)
        }
        btnAdmiManageCategory.setOnClickListener {
            var intent = Intent(this,AdminManageCategory::class.java)
            startActivity(intent)
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
            var intent = Intent(this,MainCart::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}