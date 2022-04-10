package com.example.shoppingapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.activity_main_user_page.*

class MainUserPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        var slider:ViewFlipper
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user_page)

        var actionBarDrawerToggle = ActionBarDrawerToggle(this,drawer_layout,R.string.nav_open, R.string.nav_close)
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayShowHomeEnabled(true)


        var imgArr = arrayOf(R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4)
        for(i in 0..3)
        {
            showImg(imgArr[i])
        }

        imageView4.setOnClickListener {
            Toast.makeText(this,"Electronics",Toast.LENGTH_SHORT).show()
        }
        imageView5.setOnClickListener {
            Toast.makeText(this,"Electronics",Toast.LENGTH_SHORT).show()
        }
        imageView6.setOnClickListener {
            Toast.makeText(this,"Accessories",Toast.LENGTH_SHORT).show()
        }
        imageView7.setOnClickListener {
            Toast.makeText(this,"Footwear",Toast.LENGTH_SHORT).show()
        }
        imageView8.setOnClickListener {
            Toast.makeText(this,"Home Appliances",Toast.LENGTH_SHORT).show()
        }
        imageView9.setOnClickListener {
            Toast.makeText(this,"Kids Wear",Toast.LENGTH_SHORT).show()
        }
        imageView10.setOnClickListener {
            Toast.makeText(this,"Crockery",Toast.LENGTH_SHORT).show()
        }
        imageView11.setOnClickListener {
            Toast.makeText(this,"Stationery",Toast.LENGTH_SHORT).show()
        }
        imageView12.setOnClickListener {
            Toast.makeText(this,"Male Fashion",Toast.LENGTH_SHORT).show()
        }
        imageView13.setOnClickListener {
            Toast.makeText(this,"Male Fashion",Toast.LENGTH_SHORT).show()
        }
        imageView14.setOnClickListener {
            Toast.makeText(this,"Female Fashion",Toast.LENGTH_SHORT).show()
        }
        imageView15.setOnClickListener {
            Toast.makeText(this,"Watches",Toast.LENGTH_SHORT).show()
        }
    }

    fun showImg (img:Int)
    {
        var imgview =ImageView(this)
        imgview.setBackgroundResource(img)

        slider.addView(imgview)
        slider.flipInterval(3000)
        slider.isAutoStart=true
        slider.setInAnimation(this, android.R.anim.slide_in_left)
        slider.setOutAnimation(this,android.R.anim.slide_out_right)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var item1 = item.itemId

        if(item1.equals(R.id.menuLogout))
        {
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

private fun ViewFlipper.flipInterval(i: Int) {

}
