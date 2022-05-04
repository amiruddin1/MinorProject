package com.example.shoppingapplication

class Orders constructor(var u_id:String, var p_id: Int, var p_name:String, var o_qty : Int, var o_price : Double){
    var O_id = 0;
    constructor(O_id: Int,u_id:String, p_id: Int, p_name:String, o_qty : Int, o_price : Double): this(u_id, p_id, p_name, o_qty, o_price)
    {
        this.O_id = O_id;
    }
}