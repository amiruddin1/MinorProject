package com.example.shoppingapplication

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dailog_increasestock.*
import kotlinx.android.synthetic.main.list_product_stockmanagement_recycler_structure.view.*
import kotlinx.android.synthetic.main.myordersrecycler.view.*

class List_Product_Stock_Adapter (val context: Context, var arr:ArrayList<Product>)
    : RecyclerView.Adapter<List_Product_Stock_Adapter.ViewHolder>() {

    var con = context
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(p: Product) {
            view.txtPnameStock.setText(p.p_name.toString())
            view.txtPDescStock.text = p.p_desc.toString()
            view.txtPIDStock.setText(p.p_Id.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(
            R.layout.list_product_stockmanagement_recycler_structure,
            parent,
            false
        )
        return List_Product_Stock_Adapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(arr[position])
            holder.itemView.btnIncreaseStockSM.setOnClickListener {
            var dialog = Dialog(con)
            dialog.setContentView(R.layout.dailog_increasestock)
            dialog.setCancelable(false)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.gravity = Gravity.CENTER

            dialog.window!!.attributes = lp
            dialog.show()

            dialog.btnDoneByIncreaseStock.setOnClickListener {
                if(dialog.edtNoOfNewUnits.text.toString().toInt()<0)
                {
                    Toast.makeText(con,"Must Not Less Than 0",Toast.LENGTH_LONG).show()
                }
                else
                {
                    var newStock = arr[position].p_qty.toString().toInt() + dialog.edtNoOfNewUnits.text.toString().toInt()
                    var db = DBHelper(con)
                    db.UpdateStock(arr[position].p_Id,newStock)
                    dialog.dismiss()
                    var intent = Intent(context,ManageProduct::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }
                dialog.btnCancelByStockManagement.setOnClickListener {
                    dialog.dismiss()
                    var intent = Intent(context,List_Product_StockManagement::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}