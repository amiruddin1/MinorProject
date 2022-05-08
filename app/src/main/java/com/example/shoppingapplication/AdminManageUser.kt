package com.example.shoppingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_admin_manage_category.*
import kotlinx.android.synthetic.main.activity_admin_manage_user.*

class AdminManageUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_manage_user)
        refreshList()
    }
    fun refreshList()
    {
        var db = DBHelper(this)
        var arr = db.getAllUsers()
        Toast.makeText(this,"${arr.size}",Toast.LENGTH_LONG).show()
        var ad = ManageUsersRCVAdapter(this,arr)
        RCVManageUser.adapter = ad
    }
}