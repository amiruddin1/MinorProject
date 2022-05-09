package com.example.shoppingapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "ShoppingSystemDB"
        private const val TB1_NAME = "user"
        private const val TB_PRODUCT = "Product"
        private const val TB_CATEGORY = "Category"
        private const val TB_ORDERS = "Orders"
        private const val TB_CART = "Cart"

        private const val DB_VERSION = 1

        private const val F1_USER = "U_Id"
        private const val F3_USER = "name"
        private const val F2_USER = "email"
        private const val F4_USER = "password"
        private const val F5_USER = "MobileNumber"

        private const val F1_CATEGORY = "C_Id"
        private const val F2_CATEGORY = "C_Name"

        private const val F1_PRODUCT = "P_Id"
        private const val F2_PRODUCT = "C_Id"
        private const val F3_PRODUCT = "P_Name"
        private const val F4_PRODUCT = "P_Desc"
        private const val F5_PRODUCT = "P_Price"
        private const val F6_PRODUCT = "P_Qty"

        private const val F1_ORDERS = "O_Id"
        private const val F2_ORDERS = "U_Id"
        private const val F3_ORDERS = "P_Id"
        private const val F4_ORDERS = "P_Name"
        private const val F5_ORDERS = "O_Qty"
        private const val F6_ORDERS = "O_Price"
        private const val F7_ORDERS = "O_Time"

        private const val F1_CART = "Cart_Id"
        private const val F2_CART = "P_Id"
        private const val F3_CART = "CartP_Name"
        private const val F4_CART = "CartP_Desc"
        private const val F5_CART = "CartP_Price"
        private const val F6_CART = "CartP_Qty"
        private const val F7_CART = "CartP_GrandTotal"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var query_user =
            "Create Table $TB1_NAME ($F1_USER INTEGER PRIMARY KEY AUTOINCREMENT, $F2_USER TEXT UNIQUE, $F3_USER TEXT, $F4_USER TEXT, $F5_USER TEXT)"
        var query_category =
            "Create Table $TB_CATEGORY ($F1_CATEGORY INTEGER PRIMARY KEY, $F2_CATEGORY TEXT)"
        var query_product =
            "Create Table $TB_PRODUCT ($F1_PRODUCT INTEGER PRIMARY KEY AUTOINCREMENT, $F2_PRODUCT INTEGER REFERENCES $TB_CATEGORY($F2_PRODUCT), $F3_PRODUCT TEXT, $F4_PRODUCT TEXT, $F5_PRODUCT DOUBLE, $F6_PRODUCT INTEGER)"
        var query_orders =
            "CREATE TABLE $TB_ORDERS ($F1_ORDERS INTEGER PRIMARY KEY AUTOINCREMENT, $F2_ORDERS INTEGER REFERENCES $TB1_NAME ($F2_ORDERS), $F3_ORDERS INTEGER REFERENCES $TB_PRODUCT ($F3_ORDERS),$F4_ORDERS Text, $F5_ORDERS INTEGER, $F6_ORDERS DOUBLE, $F7_ORDERS DATETIME DEFAULT CURRENT_TIMESTAMP)"
        var query_cart =
            "Create Table $TB_CART ($F1_CART INTEGER PRIMARY KEY AUTOINCREMENT, $F2_CART INTEGER REFERENCES $TB_PRODUCT($F1_PRODUCT), $F3_CART TEXT, $F4_CART TEXT, $F5_CART DOUBLE, $F6_CART INTEGER, $F7_CART DOUBLE)"


        p0?.execSQL(query_category)
        p0?.execSQL(query_product)
        p0?.execSQL(query_user)
        p0?.execSQL(query_orders)
        p0?.execSQL(query_cart)
        addCategory(p0)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        var query_user = "DROP TABLE IF EXISTS $TB1_NAME"
        p0?.execSQL(query_user)
        onCreate(p0)
    }

    fun addCategory(db: SQLiteDatabase?) {
        // var db = this.writableDatabase
        var q_c1 = "Insert Into Category Values('1','MaleClothing')"
        var q_c2 = "Insert Into Category Values('2','Electronics')";
        var q_c3 = "Insert Into Category Values('3','FemaleClothing')";
        var q_c4 = "Insert Into Category Values('4','MobileAccessories')";
        var q_c5 = "Insert Into Category Values('5','KidsWear')";
        var q_c6 = "Insert Into Category Values('6','Footwear')";
        var q_c7 = "Insert Into Category Values('7','WristWatches')";
        var q_c8 = "Insert Into Category Values('8','Crockery')";
        var q_c9 = "Insert Into Category Values('9','Stationery')";
        var q_c10 = "Insert Into Category Values('10','HomeAppliances')";
        db?.execSQL(q_c1)
        db?.execSQL(q_c2)
        db?.execSQL(q_c3)
        db?.execSQL(q_c4)
        db?.execSQL(q_c5)
        db?.execSQL(q_c6)
        db?.execSQL(q_c7)
        db?.execSQL(q_c8)
        db?.execSQL(q_c9)
        db?.execSQL(q_c10)
    }

    fun insertUser(arr: user): Long {
        var db = this.writableDatabase
        var cn = ContentValues()
        cn.put(F2_USER, arr.email)
        cn.put(F3_USER, arr.name)
        cn.put(F4_USER, arr.password)
        cn.put(F5_USER, arr.mobileNumber)
        var result = db.insert(TB1_NAME, null, cn)
        return result;
    }

    fun getUser(email: String): ArrayList<user> {
        var db = this.readableDatabase
        var arr = ArrayList<user>()
        var q = "Select * from $TB1_NAME where $F2_USER = '$email'"
        var cursor: Cursor = db.rawQuery(q, null)
        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var uemail = cursor.getString(1)
            var name = cursor.getString(2)
            var password = cursor.getString(3)
            var mobileNumber = cursor.getString(4)
            var uss = user(id, uemail, name, password, mobileNumber)
            arr.add(uss)
        }
        return arr
    }

    fun getAllUsers(): ArrayList<user> {
        var db = this.readableDatabase
        var arr = ArrayList<user>()
        var q = "Select * from $TB1_NAME"
        var cursor: Cursor = db.rawQuery(q, null)
        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var uemail = cursor.getString(1)
            var name = cursor.getString(2)
            var password = cursor.getString(3)
            var mobileNumber = cursor.getString(4)
            var uss = user(id, uemail, name, password, mobileNumber)
            arr.add(uss)
        }
        return arr
    }

    fun AddProduct(arr: Product): Long {
        var db = this.readableDatabase
        var cn = ContentValues()
        cn.put(F2_PRODUCT, arr.c_Id)
        cn.put(F3_PRODUCT, arr.p_name)
        cn.put(F4_PRODUCT, arr.p_desc)
        cn.put(F5_PRODUCT, arr.p_price)
        cn.put(F6_PRODUCT, arr.p_qty)
        var result = db.insert(TB_PRODUCT, null, cn)
        return result
    }

    fun DeleteProduct(id:Int):Int
    {
        var db=writableDatabase
        var res = db.delete(TB_PRODUCT,"$F1_PRODUCT=$id",null)
        db.close()
        return res
    }

    fun UpdateProduct(p:Product) : Int
    {
        var db=writableDatabase
        var cv=ContentValues()
        cv.put(F2_PRODUCT,p.c_Id)
        cv.put(F3_PRODUCT,p.p_name)
        cv.put(F4_PRODUCT,p.p_desc)
        cv.put(F5_PRODUCT,p.p_price)
        cv.put(F6_PRODUCT,p.p_qty)
        var flag=db.update(TB_PRODUCT,cv,"$F1_PRODUCT=${p.p_Id}",null)
        db.close()
        return flag
    }

    fun RetrieveAllProduct(): ArrayList<Product> {
        var arr = ArrayList<Product>()
        var db = readableDatabase
        var cursor = db.query(TB_PRODUCT, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var c_id = cursor.getInt(1)
            var name = cursor.getString(2)
            var desc = cursor.getString(3)
            var price = cursor.getDouble(4)
            var qty = cursor.getInt(5)
            var p = Product(id, c_id, name, desc, price, qty)
            arr.add(p)
        }
        return arr
    }

    fun getuserOrders(str: String?): ArrayList<Orders> {
        var arr = ArrayList<Orders>()
        var db = readableDatabase
        var q =
            "Select * from $TB_ORDERS where $F2_ORDERS =  '$str' "

        //SELECT * FROM ORDERS WHERE U_ID = (SELECT U_ID FROM USERS WHERE NAME = 'AMIRUDDIN SAMLAYAWALA')

        var cursor: Cursor = db.rawQuery(q, null)
        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var U_id = cursor.getString(1)
            var p_id = cursor.getInt(2)
            var p_name = cursor.getString(3)
            var qty = cursor.getInt(4)
            var price = cursor.getDouble(5)
            var o = Orders(id, U_id, p_id, p_name, qty, price)
            arr.add(o)
        }
        return arr
    }

    fun GetNoofThings(): Array<Int> {
        var db = readableDatabase
        var que = "Select * from $TB_PRODUCT";
        var que2 = "Select * from $TB_CATEGORY"
        var que3 = "Select * from $TB1_NAME";

        var res = db.rawQuery(que, null)
        var temp1 = 0
        while (res.moveToNext()) {
            temp1++
        }
        var res2 = db.rawQuery(que2, null)
        var temp2 = 0
        while (res2.moveToNext()) {
            temp2++
        }
        var res3 = db.rawQuery(que3, null)
        var temp3 = 0
        while (res3.moveToNext()) {
            temp3++
        }
        var arr: Array<Int> = arrayOf(temp1, temp2, temp3)

        return arr
    }

    fun getProductsforbrose(str: String?) : ArrayList<Product>
    {
        var db = readableDatabase
        var query =
            "Select * from $TB_PRODUCT Where $F2_PRODUCT = (Select $F1_CATEGORY from $TB_CATEGORY Where $F2_CATEGORY = '$str')"
        var cursor = db.rawQuery(query, null)
        var arr = ArrayList<Product>()
        while (cursor.moveToNext())
        {
            var id= cursor.getInt(0)
            var category = cursor.getInt(1)
            var name = cursor.getString(2)
            var desc = cursor.getString(3)
            var price = cursor.getDouble(4)
            var qty = cursor.getInt(5)
            var product = Product(id,category,name,desc,price,qty)
            arr.add(product)
        }
        return arr
    }

    fun getAllCategories() : ArrayList<String>
    {
        var db = readableDatabase
        var query =
            "Select $F2_CATEGORY from $TB_CATEGORY"
        var cursor = db.rawQuery(query, null)
        var arr = ArrayList<String>()
        while (cursor.moveToNext())
        {
            var name = cursor.getString(0).toString()
            arr.add(name)
        }
        return arr
    }

    fun getAllTheCategories() : ArrayList<Category>
    {
        var db = readableDatabase
        var query =
            "Select * from $TB_CATEGORY"
        var cursor = db.rawQuery(query, null)
        var arr = ArrayList<Category>()
        while (cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var name = cursor.getString(1).toString()
            var c = Category(id,name)
            arr.add(c)
        }
        return arr
    }

    fun cnameToCId(str :String) : Int
    {
        var db = readableDatabase
        var query =
            "Select $F1_CATEGORY from $TB_CATEGORY where $F2_CATEGORY = '$str'"
        var cursor = db.rawQuery(query, null)
        var arr = ArrayList<Int>()
        while (cursor.moveToNext())
        {
            var id = cursor.getString(0).toString().toInt()
            arr.add(id)
        }
        return arr[0].toString().toInt()
    }

    fun cIdtoName(id:Int) : String
    {
        var db = readableDatabase
        var query =
            "Select $F2_CATEGORY from $TB_CATEGORY where $F1_CATEGORY = '$id'"
        var cursor = db.rawQuery(query, null)
        var arr = ArrayList<String>()
        while (cursor.moveToNext())
        {
            var name = cursor.getString(0).toString()
            arr.add(name)
        }
        return arr[0].toString()
    }

    fun getUserInfo(str : String?) : ArrayList<user>
    {
        var db = readableDatabase
        var arr =  ArrayList<user>()
        var q = "Select * from user where $F3_USER = '$str'"
        var cursor = db.rawQuery(q,null)
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var email = cursor.getString(1)
            var name = cursor.getString(2)
            var pass = cursor.getString(3)
            var mobileNumber = cursor.getString(4)
            var us = user(id,email,name,pass,mobileNumber)
            arr.add(us)
        }
        return arr
    }

    fun getProductByItsID(str: Int?) : ArrayList<Product>
    {
        var db = readableDatabase
        var query =
            "Select * from $TB_PRODUCT Where $F1_PRODUCT = $str"
        var cursor = db.rawQuery(query, null)
        var arr = ArrayList<Product>()
        while (cursor.moveToNext())
        {
            var id= cursor.getInt(0)
            var category = cursor.getInt(1)
            var name = cursor.getString(2)
            var desc = cursor.getString(3)
            var price = cursor.getDouble(4)
            var qty = cursor.getInt(5)
            var product = Product(id,category,name,desc,price,qty)
            arr.add(product)
        }
        return arr
    }

    fun AddProIntoCart(c:Cart) :Long
    {
        var db = this.readableDatabase
        var cn = ContentValues()
        cn.put(F2_CART, c.pId)
        cn.put(F3_CART, c.pName)
        cn.put(F4_CART, c.pDesc)
        cn.put(F5_CART, c.pPrice)
        cn.put(F6_CART, c.pQty)
        cn.put(F7_CART, c.pGTotal)
        var result = db.insert(TB_CART, null, cn)
        return result
    }

    fun getCartedProduct() : ArrayList<Cart>
    {
        var db = readableDatabase
        var query =
            "Select * from $TB_CART"
        var cursor = db.rawQuery(query, null)
        var arr = ArrayList<Cart>()
        while (cursor.moveToNext())
        {
            var id= cursor.getInt(0)
            var pid = cursor.getInt(1)
            var pname = cursor.getString(2)
            var pdesc = cursor.getString(3)
            var pprice = cursor.getDouble(4)
            var pqty = cursor.getInt(5)
            var gt = cursor.getDouble(6)
            var cart = Cart(id,pid,pname,pdesc,pprice,pqty,gt)

            arr.add(cart)
        }
        return arr
    }

    fun RemoveFromCart(id:Int) : Int
    {
        var db = readableDatabase
        var del= db.delete(TB_CART, F1_CART + " = " + id,null)
        return del
    }

    fun PlaceOrder(arr :Cart,uid:String?): Long
    {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put(F2_ORDERS,uid)
        cn.put(F3_ORDERS,arr.pId)
        cn.put(F4_ORDERS,arr.pName)
        cn.put(F5_ORDERS,arr.pQty)
        cn.put(F6_ORDERS,arr.pGTotal)
        var res = db.insert(TB_ORDERS,null,cn)
        return res
    }

    fun AddCategory (arr : Category) : Long
    {
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(F2_CATEGORY,arr.c_name)

        var res = db.insert(TB_CATEGORY,null,cv)
        return res
    }

    // FUNCTION OF INCREASE STOCK MANAGEMENT
    fun GetStock(id:Int) : Int
    {
        var arr = ArrayList<Int>()
        var db = writableDatabase
        var query =
            "Select * from $TB_PRODUCT Where $F1_PRODUCT = '$id'"
        var cur = db.rawQuery(query,null)
        while(cur.moveToNext())
        {
            var stock = cur.getInt(5)
            arr.add(stock)
        }
        return arr[0]
    }

    fun UpdateStock(id:Int,value:Int)
    {
        var db = writableDatabase
        var query =
            "Update $TB_PRODUCT set $F6_PRODUCT = '$value' Where $F1_PRODUCT = '$id'"
        db.execSQL(query)
    }

    fun GetTotalSale() : Int
    {
        var arr = ArrayList<Int>()
        var db = readableDatabase
        var sql =
            "select sum($F6_ORDERS) from $TB_ORDERS"
        var cursor = db.rawQuery(sql,null)
        while(cursor.moveToNext())
        {
            var result = cursor.getInt(0)
            arr.add(result)
        }
        return arr[0]
    }
}
