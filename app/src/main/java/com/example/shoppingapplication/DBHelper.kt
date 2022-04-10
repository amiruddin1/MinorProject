package com.example.shoppingapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    companion object{
        private const val DB_NAME = "ShoppingSystemDB"
        private const val TB1_NAME = "admin"
        private const val TB2_NAME = "user"
        private const val DB_VERSION = 1

        private const val F1_USER = "Id"
        private const val F3_USER = "name"
        private const val F2_USER = "email"
        private const val F4_USER = "password"
        private const val F5_USER = "MobileNumber"

        private const val F1_ADMIN = "AId"
        private const val F3_ADMIN = "Aname"
        private const val F2_ADMIN = "Aemail"
        private const val F4_ADMIN = "Apassword"
        private const val F5_ADMIN = "AMobileNumber"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var query_user = "Create Table $TB2_NAME ($F1_USER INTEGER PRIMARY KEY AUTOINCREMENT, $F2_USER TEXT UNIQUE, $F3_USER TEXT, $F4_USER TEXT, $F5_USER TEXT)"
        var query_admin = "Create Table $TB1_NAME ($F1_ADMIN INTEGER PRIMARY KEY AUTOINCREMENT, $F2_ADMIN TEXT UNIQUE, $F3_ADMIN TEXT, $F4_ADMIN TEXT, $F5_ADMIN TEXT)"
        var q3 = "Insert into $TB1_NAME values('amiruddin123@gmail.com','Amiruddin Samlayawala','test1234','6351715055')"
        var q4 = "Insert into $TB1_NAME values('shivam123@gmail.com','Shivam Utsurge','test1234','9876543211')"
        p0?.execSQL(query_user)
        p0?.execSQL(query_admin)
        p0?.execSQL(q3)
        p0?.execSQL(q4)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        var query_user = "DROP TABLE IF EXISTS $TB1_NAME"
        var query_admin = "DROP TABLE IF EXISTS $TB2_NAME"
        p0?.execSQL(query_user)
        p0?.execSQL(query_admin)
        onCreate(p0)
    }

//    fun insertUser(arr:user) : Long
//    {
//        var db = this.writableDatabase
//        var cn = ContentValues()
//        cn.put(F2_USER,arr.email)
//        cn.put(F3_USER,arr.name)
//        cn.put(F4_USER,arr.password)
//        cn.put(F5_USER,arr.mobileNumber)
//        var result = db.insert(TB2_NAME,null,cn)
//        return result;
//    }
//    fun getUser(email:String) : user
//    {
//        var u = user(0,"","","","");
//        var db = this.readableDatabase
//        var q= "Select * from $TB2_NAME where $F2_USER = $email"
//        var cursor:Cursor = db.rawQuery(q,null)
//            while(cursor.moveToNext())
//            {
//                var id = cursor.getInt(0)
//                var email = cursor.getString(1)
//                var name = cursor.getString(2)
//                var password = cursor.getString(3)
//                var pno = cursor.getString(4)
//                u = user(id, email, name, password, pno)
//            }
//        return u;
//    }
//    fun getAdmin(email:String) : admin
//    {
//        var a = admin(0,"","","","");
//        var db = this.readableDatabase
//        var q= "Select * from $TB1_NAME where $F2_ADMIN = $email"
//        var cursor:Cursor = db.rawQuery(q,null)
//        while(cursor.moveToNext())
//        {
//            var id = cursor.getInt(0)
//            var email = cursor.getString(1)
//            var name = cursor.getString(2)
//            var password = cursor.getString(3)
//            var pno = cursor.getString(4)
//            a = admin(id, email, name, password, pno)
//        }
//        return a;
//    }
}