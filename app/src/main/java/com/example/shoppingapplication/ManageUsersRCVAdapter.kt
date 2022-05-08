package com.example.shoppingapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.managecategoryrecycler.view.*
import kotlinx.android.synthetic.main.manageusersrecycler.view.*

class ManageUsersRCVAdapter (val context: Context, var arr:ArrayList<user>)
    : RecyclerView.Adapter<ManageUsersRCVAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(ct: user) {
            view.txtmanageUserUname.setText(ct.name)
            view.txtManageUserUEmail.setText(ct.email)
            view.txtManageUserPhoneNumber.setText(ct.mobileNumber)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.manageusersrecycler,parent,false)
        return ManageUsersRCVAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}