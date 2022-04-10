package com.example.shoppingapplication

class admin constructor (var Aemail:String, var Aname:String, var Apassword:String, var AmobileNumber: String) {
    var Aid = 0;
    constructor(Aid:Int,Aemail:String,Aname: String,Apassword: String,AmobileNumber: String): this(Aemail,Aname,Apassword,AmobileNumber)
    {
        this.Aid = Aid;
    }
}