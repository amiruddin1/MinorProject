package com.example.shoppingapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.myproductrecyclerview.view.*

class MyProductAdapter (val context: Context, var arr:ArrayList<Product>)
    : RecyclerView.Adapter<MyProductAdapter.ViewHolder>() {


    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(p: Product) {
            view.txtRecyclerProductId.setText(p.p_Id.toString())
            view.txtRecyclerProductName.setText(p.p_name)
            view.txtRecyclerProductDesc.setText(p.p_desc)
            view.txtRecyclerProductPrice.setText(p.p_price.toString())
            view.txtRecyclerProductQty.setText(p.p_qty.toString())

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.myproductrecyclerview,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
        holder.itemView.btnUpdateMyRecyclerData.setOnClickListener {
            Toast.makeText(context,"Hello", Toast.LENGTH_LONG).show()
            //context.delete(position)
        }
        holder.itemView.btnDeleteMyRecyclerData.setOnClickListener {
            Toast.makeText(context,"working", Toast.LENGTH_LONG).show()
                //context.update(position)
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}