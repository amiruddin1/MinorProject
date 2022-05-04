package com.example.shoppingapplication

class Product constructor(var c_Id:Int, var p_name:String, var p_desc:String, var p_price:Double, var p_qty:Int) {
    var p_Id:Int = 0;
    constructor(p_Id:Int,c_Id: Int,p_name:String,p_desc: String,p_price: Double,p_qty: Int): this(c_Id,p_name,p_desc,p_price,p_qty)
    {
        this.p_Id = p_Id
    }
}