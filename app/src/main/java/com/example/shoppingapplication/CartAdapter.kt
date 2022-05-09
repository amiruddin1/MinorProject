package com.example.shoppingapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cartproductrecycler.view.*
import kotlinx.android.synthetic.main.myproductrecyclerview.view.*


class CartAdapter(val context: Context, var arr:ArrayList<Cart>)
    : RecyclerView.Adapter<CartAdapter.ViewHolder>()  {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(p: Cart) {
            view.txtRecycleCartCartId.setText(p.cartId.toString())
            view.txtRecyclerCartProductId.setText(p.pId.toString())
            view.txtRecyclerCartProductName.setText(p.pName)
            view.txtRecyclerCartProductDesc.setText(p.pDesc)
            view.txtRecyclerCartProductPrice.setText(p.pPrice.toString())
            view.txtRecyclerCartProductQty.setText(p.pQty.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.cartproductrecycler,parent,false)
        return CartAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
        holder.view.btnRemoveFromCart.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Title")
                .setMessage("Do you really want to Remove Product From Cart?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                    DialogInterface.OnClickListener { dialog, whichButton ->
                        var db = DBHelper(context)
                        var res= db.RemoveFromCart(arr[position].cartId)

                        if(res > 0)
                        {
                            Toast.makeText(context,"Product Removed From Cart",Toast.LENGTH_LONG).show()
                            var intent = Intent(context, MainUserPage::class.java)
                            var sp: SharedPreferences = context.getSharedPreferences("CartingProduct",
                                AppCompatActivity.MODE_PRIVATE)
                            var prefeditor = sp.edit()
                            prefeditor.commit()

                            context.startActivity(intent)
                            (context as MainCart).finish()
                        }
                        else{
                            Toast.makeText(context,"Error!",Toast.LENGTH_LONG).show()
                        }
                    })
                .setNegativeButton(android.R.string.no, null).show()
        }
        holder.view.btnProceedToPay.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Title")
                .setMessage("Do you really want to Place the Order?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                    DialogInterface.OnClickListener { dialog, whichButton ->
                        var db = DBHelper(context)
                        var preference: SharedPreferences = context.getSharedPreferences("mypref",
                            AppCompatActivity.MODE_PRIVATE
                        )
                        var str = preference.getString("UserName","tt")
                        var res = db.PlaceOrder(arr[position],str)
                        if(res > 0)
                        {
                            var res= db.RemoveFromCart(arr[position].pId)
                            Toast.makeText(context,"Product Ordered Successfully.",Toast.LENGTH_LONG).show()
                            var intent = Intent(context, MainUserPage::class.java)
                            var sp: SharedPreferences = context.getSharedPreferences("CartingProduct",
                                AppCompatActivity.MODE_PRIVATE)
                            var prefeditor = sp.edit()
                            prefeditor.commit()

                            context.startActivity(intent)
                            (context as MainCart).finish()
                        }
                        else{
                            Toast.makeText(context,"Error!",Toast.LENGTH_LONG).show()
                        }
                    })
                .setNegativeButton(android.R.string.no, null).show()
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}