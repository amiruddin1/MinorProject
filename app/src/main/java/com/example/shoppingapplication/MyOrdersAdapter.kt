package com.example.shoppingapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.myordersrecycler.view.*
import kotlinx.android.synthetic.main.myproductrecyclerview.view.*

class MyOrdersAdapter (val context: Context, var arr:ArrayList<Orders>)
    : RecyclerView.Adapter<MyOrdersAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(p: Orders) {
            view.txtmyordersOrderId.setText(p.O_id.toString())
            view.txtMyOrdersProductName.setText(p.p_name)
            view.txtMyOrdersProductId.text = p.p_id.toString()
            view.txtMyOrdersProductPrice.text = (p.o_price/p.o_qty).toString()
            view.txtMyOrdersProductQuantity.text = p.o_qty.toString()
            view.txtMyOrdersTotalAmount.text = (p.o_price).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.myordersrecycler,parent,false)
        return MyOrdersAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}