package com.example.shoppingapplication

class user constructor(var email:String, var name:String, var password:String, var mobileNumber: String){
    var id = 0;
    constructor(id:Int,email:String,name: String,password: String,mobileNumber: String): this(email,name,password,mobileNumber)
    {
        this.id = id;
    }
}