package com.example.barbershop.appointments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.R
import com.example.barbershop.data.objects.Appointments

class AppointmentRecyclerView(private val list:List<Appointments>,private val click:ClickListener):
    RecyclerView.Adapter<AppointmentRecyclerView.ViewHolder>() {

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val title=item.findViewById<TextView>(R.id.itemTitle)
        val itemDetails=item.findViewById<TextView>(R.id.itemDetails)
        val itemTotal=item.findViewById<TextView>(R.id.itemTotal)
        val itemService=item.findViewById<TextView>(R.id.itemProduct)

        val imageView=itemView.findViewById<ImageView>(R.id.delete)

        lateinit var i:Appointments

        init {
            imageView.setImageResource(R.drawable.ic_baseline_archive_24)

            imageView.setOnClickListener {
                click.onItemClick(i)
            }
        }
    }
    interface ClickListener{
        fun onItemClick(item:Appointments)
    }
    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        layoutInflater= LayoutInflater.from(parent.context)

        val view=layoutInflater.inflate(R.layout.item_list,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list[position]

        holder.i=item

        holder.title.text=item.customerName

        var status="pending"

        if(item.status==3){
            status="cancelled"

            holder.itemTotal.text=status

            holder.itemTotal.setTextColor(Color.RED)
        }

        holder.itemService.text=item.product

        holder.itemDetails.text=item.appointment_date+" "+item.appointment_time
    }

    override fun getItemCount()=list.size

}