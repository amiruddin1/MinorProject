package com.example.shoppingapplication

class Cart constructor(var pId: Int,var pName: String,var pDesc:String,var pPrice:Double,var pQty:Int,var pGTotal:Double) {
    var cartId:Int = 0
    constructor(cartId:Int,pId: Int,pName: String,pDesc: String,pPrice: Double,pQty: Int,pGTotal: Double): this(pId,pName,pDesc,pPrice,pQty,pGTotal)
    {
        this.cartId = cartId
    }
}