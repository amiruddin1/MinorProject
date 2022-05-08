package com.example.shoppingapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.managecategoryrecycler.view.*
import kotlinx.android.synthetic.main.myordersrecycler.view.*

class ManageCategoryRCVAdapter (val context: Context, var arr:ArrayList<Category>)
    : RecyclerView.Adapter<ManageCategoryRCVAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(ct: Category) {
            view.txtManageCategoryRCVID.setText(ct.c_Id.toString())
            view.txtManageCategoryRCVName.setText(ct.c_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.managecategoryrecycler,parent,false)
        return ManageCategoryRCVAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}