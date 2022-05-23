package com.example.barbershop.customers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.R
import com.example.barbershop.data.objects.Customers

class CustomersRecyclerView(private val list:List<Customers>):RecyclerView.Adapter<CustomersRecyclerView.ViewHolder>() {

    inner class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        val title=item.findViewById<TextView>(R.id.itemTitle)
        val itemDetails=item.findViewById<TextView>(R.id.itemDetails)
    }
    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        layoutInflater= LayoutInflater.from(parent.context)

        val view=layoutInflater.inflate(R.layout.item_with_delete,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list[position]

        holder.title.text=item.username

        holder.itemDetails.text=item.gender
    }

    override fun getItemCount()=list.size

}