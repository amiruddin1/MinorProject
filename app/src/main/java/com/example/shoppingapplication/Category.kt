package com.example.shoppingapplication

class Category (var c_name:String) {
    var c_Id:Int = 0;
    constructor(c_ID:Int, c_name: String):this(c_name)
    {
        this.c_Id = c_ID
    }
}

/*
* 1) Male Clothing
* 2) Electronics
* 3) Female Clothing
* 4) Mobile Accessories
* 5) Kids Wear
* 6) Footwear
* 7) WristWatches
* 8) Crokeries
* 9) Stationery
* 10) Home Appliances
* */