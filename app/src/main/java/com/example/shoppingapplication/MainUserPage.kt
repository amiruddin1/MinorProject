package com.example.shoppingapplication

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main_user_page.*
import kotlinx.android.synthetic.main.activity_main_user_page.spinner
import kotlinx.android.synthetic.main.dialogue_addproduct.*
import kotlinx.android.synthetic.main.myaccountdetaildialog.*

class MainUserPage : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    //TODO: SEARCH FUNTIONALITY SHOULD BE ADDED...
    var categories = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        var slider:ViewFlipper
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user_page)
        navigate.setNavigationItemSelectedListener(this)

        categories.add("Select Category")
        var dbt = DBHelper(this)
        for (i in 0 .. dbt.getAllCategories().size-1){
            categories.add(dbt.getAllCategories()[i].toString())
        }

        spinner.onItemSelectedListener = this
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(this,android.R.layout.simple_spinner_item,
            categories as List<Any?>
        )
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = ad

        btnGoUserPage.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "${spinner.selectedItem}")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }


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
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "Electronics")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView5.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "Electronics")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView6.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "MobileAccessories")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView7.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "Footwear")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView8.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "HomeAppliances")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView9.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "KidsWear")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView10.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "Crockery")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView11.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "Stationery")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView12.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "MaleClothing")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView13.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "MaleClothing")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView14.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "FemaleClothing")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
        }
        imageView15.setOnClickListener {
            var preference: SharedPreferences = getSharedPreferences("MyCategory", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.putString("UserName", "WristWatches")
            prefeditor.commit()
            var intent = Intent(this,Browse_product::class.java)
            startActivity(intent)
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(applicationContext,
            categories[p2],
            Toast.LENGTH_LONG)
            .show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var item1 = item.itemId

        if(item1.equals(R.id.menuLogout))
        {
            var preference:SharedPreferences=getSharedPreferences("mypref", MODE_PRIVATE)
            var prefeditor = preference.edit()
            prefeditor.clear()
            prefeditor.commit()
            var intent = Intent(this,MainLoginPage::class.java)
            startActivity(intent)
            finish()
        }
        else if(item1.equals(R.id.menuShoppingCart))
        {
            var sp :SharedPreferences = getSharedPreferences("CartingProduct",AppCompatActivity.MODE_PRIVATE)
            var prefEditor = sp.edit()
            prefEditor.clear()
            prefEditor.commit()

            var intent = Intent(this,MainCart::class.java)
            startActivity(intent)
        }
        else if(item1.equals(R.id.menuMyOrders))
        {
            var intent = Intent(this,MyOrdersMain::class.java)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        when(id)
        {
            R.id.nav_account -> {
                var preference: SharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE)
                var str = preference.getString("UserName", "tt")

                Toast.makeText(this,"$str",Toast.LENGTH_LONG).show()
                var db = DBHelper(this)
                var arr = db.getUserInfo(str)

                var dialog = Dialog(this)
                dialog.setContentView(R.layout.myaccountdetaildialog)
                dialog.setCancelable(false)
                val lp = WindowManager.LayoutParams()
                lp.copyFrom(dialog.window!!.attributes)
                lp.width = WindowManager.LayoutParams.MATCH_PARENT
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT
                lp.gravity = Gravity.CENTER
                dialog.window!!.attributes = lp


                dialog.show()
                dialog.textView24.setText(arr[0].name)
                dialog.textView26.setText(arr[0].email)
                dialog.textView28.setText(arr[0].mobileNumber)
                dialog.btnGotitMyProfile.setOnClickListener {
                    dialog.dismiss()
                }
            }
            R.id.nav_cart -> {
                var intent = Intent(this, MainCart::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> {
                var preference:SharedPreferences=getSharedPreferences("mypref", MODE_PRIVATE)
                var prefeditor = preference.edit()
                prefeditor.clear()
                prefeditor.commit()
                var intent = Intent(this,MainLoginPage::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_orders -> {
                var intent = Intent(this, MyOrdersMain::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}

private fun ViewFlipper.flipInterval(i: Int) {

}
