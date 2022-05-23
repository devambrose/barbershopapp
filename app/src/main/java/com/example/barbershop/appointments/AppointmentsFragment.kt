package com.example.barbershop.appointments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.R
import com.example.barbershop.changeFragment
import com.example.barbershop.data.objects.Appointments
import com.example.barbershop.servicemanager.ServicesViewModel
import com.example.barbershop.showMessage
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AppointmentsFragment:Fragment(),AppointmentRecyclerView.ClickListener {
    private lateinit var viewModel: ServicesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.appointment_fragment, container, false)

        viewModel= ServicesViewModel(requireActivity().application)

        viewModel.getAppointments().observe(viewLifecycleOwner){
            val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerViewList)

            recyclerView.layoutManager=LinearLayoutManager(requireContext())

            recyclerView.adapter=AppointmentRecyclerView(it,this)
        }

        view.findViewById<FloatingActionButton>(R.id.floatingID).setOnClickListener {
            changeFragment(requireActivity().supportFragmentManager,AppointmentFragment(),Bundle())
        }
        return view

    }

    override fun onItemClick(item: Appointments) {
        showMessage("$item")
    }
}