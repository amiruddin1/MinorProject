package com.example.shoppingapplication

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialogue_addproduct.*
import kotlinx.android.synthetic.main.dialogue_updateproduct.*
import kotlinx.android.synthetic.main.myproductrecyclerview.view.*

class MyProductAdapter (val context: Context, var arr:ArrayList<Product>)
    : RecyclerView.Adapter<MyProductAdapter.ViewHolder>() {

        var con:Context = context
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
            //TODO: Update Code Will Be Here
//            var intent = Intent(context,UpdateProductHere::class.java)
//            context.startActivity(intent)
            var dialog = Dialog(con)
            dialog.setContentView(R.layout.dialogue_updateproduct)
            dialog.setCancelable(false)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.gravity = Gravity.CENTER

            dialog.window!!.attributes = lp
            dialog.show()
            dialog.edtUpdateproductnameDialogue.setText(arr[position].p_name)
            dialog.edtUpdateproductdescDialogue.setText(arr[position].p_desc)
            dialog.edtUpdateproductpriceDialogue.setText(arr[position].p_price.toString())
            dialog.edtUpdateproductquantityDialogue.setText(arr[position].p_qty.toString())
            var db = DBHelper(context)
            var res = db.cIdtoName(arr[position].c_Id)
            dialog.txtUpdateProductCategory.setText(res)

            dialog.btncloseUpdateProductDialogue.setOnClickListener {
                dialog.dismiss()
            }
            dialog.btnUpdateproductfinally.setOnClickListener {
                var up = Product(arr[position].p_Id,arr[position].c_Id,dialog.edtUpdateproductnameDialogue.text.toString(),dialog.edtUpdateproductdescDialogue.text.toString(),dialog.edtUpdateproductpriceDialogue.text.toString().toDouble(), dialog.edtUpdateproductquantityDialogue.text.toString().toInt())
                var db = DBHelper(con)
                var result = db.UpdateProduct(up)
                if(result>0)
                {
                    Toast.makeText(con,"Product Updated Successfully.",Toast.LENGTH_LONG).show()
                    var intent = Intent(context,ManageProduct::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }

        }
        holder.itemView.btnDeleteMyRecyclerData.setOnClickListener {
            var db = DBHelper(context)
            var res = db.DeleteProduct(arr[position].p_Id)

            if(res>0)
            {
                Toast.makeText(context,"Product Deleted Successfully.", Toast.LENGTH_LONG).show()
                var intent = Intent(context,MainAdminPage::class.java)
                context.startActivity(intent)
                (context as Activity).finish()
            }
            else
            {
                Toast.makeText(context,"Having Problems..", Toast.LENGTH_LONG).show()
                var intent = Intent(context,MainAdminPage::class.java)
                context.startActivity(intent)
                (context as Activity).finish()
            }
                //context.update(position)
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}