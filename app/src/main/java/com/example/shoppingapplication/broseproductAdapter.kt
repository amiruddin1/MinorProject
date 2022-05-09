package com.example.shoppingapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.brose_product_recycler.view.*
import kotlinx.android.synthetic.main.myproductrecyclerview.view.*

class broseproductAdapter (val context: Context, var arr:ArrayList<Product>)
    : RecyclerView.Adapter<broseproductAdapter.ViewHolder>() {


    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(p: Product) {
            view.txtbroseproductid.setText(p.p_Id.toString())
            view.txtbroseproductpname.setText(p.p_name)
            view.txtbroseproductdesc.setText(p.p_desc)
            view.txtbroseproductprice.setText(p.p_price.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.brose_product_recycler,parent,false)
        return broseproductAdapter.ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        holder.bind(arr[position])
        holder.itemView.btnbroseaddtocart.setOnClickListener {
            var sp :SharedPreferences = context.getSharedPreferences("CartingProduct",AppCompatActivity.MODE_PRIVATE)
            var prefEditor = sp.edit()
            prefEditor.putString("CartProduct",arr[position].p_Id.toString())
            prefEditor.putString("CartQTY",holder.itemView.edtbroseproductqty.text.toString())
            prefEditor.commit()

            var db = DBHelper(context)
            var res = db.GetStock(arr[position].p_Id)
            if(res.toString().toInt() > holder.itemView.edtbroseproductqty.text.toString().toInt())
            {
                db.UpdateStock(arr[position].p_Id,res - holder.itemView.edtbroseproductqty.text.toString().toInt())
                var intent = Intent(context, MainCart::class.java)
                context.startActivity(intent)
                (context as Browse_product).finish()
            }
            else
            {
                Toast.makeText(context, "Product Out of Stock!! Stay Tuned!",Toast.LENGTH_LONG).show()
            }
        }
        holder.itemView.btnbrosebuynow.setOnClickListener {
            var sp :SharedPreferences = context.getSharedPreferences("CartingProduct",AppCompatActivity.MODE_PRIVATE)
            var prefEditor = sp.edit()
            prefEditor.putString("CartProduct",arr[position].p_Id.toString())
            prefEditor.putString("CartQTY",holder.itemView.edtbroseproductqty.text.toString())
            prefEditor.commit()

            var db = DBHelper(context)
            var res = db.GetStock(arr[position].p_Id)
            if(res.toString().toInt() > holder.itemView.edtbroseproductqty.text.toString().toInt())
            {
                db.UpdateStock(arr[position].p_Id,res - holder.itemView.edtbroseproductqty.text.toString().toInt())
                var intent = Intent(context, MainCart::class.java)
                context.startActivity(intent)
                (context as Browse_product).finish()
            }
            else
            {
                Toast.makeText(context, "Product Out of Stock!! Stay Tuned!",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}