package com.example.barbershop

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.data.objects.ServiceObject

class ServicesRecyclerView(private val list:List<ServiceObject>,private val clickListener:ClickListener)
    :RecyclerView.Adapter<ServicesRecyclerView.ViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemTitle=itemView.findViewById<TextView>(R.id.itemTitle)

        val itemDetails=itemView.findViewById<TextView>(R.id.itemDetails)

        val delete=itemView.findViewById<ImageView>(R.id.delete)

        lateinit var item:ServiceObject

        init {
            delete.setOnClickListener {
                clickListener.onDeleteItem(item)
            }
        }
    }

    interface ClickListener{
        fun onDeleteItem(item:ServiceObject)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        layoutInflater= LayoutInflater.from(parent.context)

       val view= layoutInflater.inflate(R.layout.item_with_delete,parent,false)

       return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list[position]

        holder.item=item

        holder.itemTitle.setText(item.name)

        holder.itemDetails.setText(item.price)
    }



    override fun getItemCount()=list.size

}