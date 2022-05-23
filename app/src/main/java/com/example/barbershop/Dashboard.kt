package com.example.barbershop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.barbershop.appointments.AppointmentFragment
import com.example.barbershop.appointments.AppointmentRecyclerView
import com.example.barbershop.data.objects.Appointments
import com.example.barbershop.servicemanager.ServicesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Dashboard:Fragment(),AppointmentRecyclerView.ClickListener {
    private lateinit var viewModel:ServicesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.appointment_fragment, container, false)

        val sharedPreferences=requireActivity().getSharedPreferences(
            Globals.APP_NAME,
            Context.MODE_PRIVATE
        )

        viewModel=ServicesViewModel(requireActivity().application)

        val user_name=sharedPreferences.getString(Globals.USER_NAME,"").toString()

        val userId = sharedPreferences.getInt(Globals.USER_ID,0)

        (activity as AppCompatActivity).supportActionBar?.title = user_name

        val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerViewList)

        if(userId !=0){
            viewModel.getMyAppointments(userId).observe(viewLifecycleOwner)
            {
                recyclerView.layoutManager=LinearLayoutManager(requireContext())

                recyclerView.adapter=AppointmentRecyclerView(it,this)
            }
        }else{
            viewModel.getAppointments().observe(viewLifecycleOwner)
            {
                recyclerView.layoutManager=LinearLayoutManager(requireContext())

                recyclerView.adapter=AppointmentRecyclerView(it,this)
            }
        }




        view.findViewById<FloatingActionButton>(R.id.floatingID).setOnClickListener {

            changeFragment(requireActivity().supportFragmentManager,AppointmentFragment(),Bundle())

        }


        return  view

    }

    override fun onItemClick(item: Appointments) {
         val dialog= SweetAlertDialog(requireContext(),SweetAlertDialog.WARNING_TYPE)

        dialog.setTitle("BarberShop Application")

        dialog.setContentText("This action is irreversibe !")

        dialog.setCancelClickListener {
            dialog.hide()
        }
        dialog.setConfirmClickListener {
            viewModel.updateAppointment(item.id,3)
            dialog.hide()
        }

        dialog.show()
    }

}