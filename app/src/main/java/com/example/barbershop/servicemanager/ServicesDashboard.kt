package com.example.barbershop.servicemanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.R
import com.example.barbershop.ServicesRecyclerView
import com.example.barbershop.changeFragment
import com.example.barbershop.data.objects.ServiceObject
import com.example.barbershop.showMessage
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ServicesDashboard:Fragment(), ServicesRecyclerView.ClickListener {

    private lateinit var viewModel:ServicesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.appointment_fragment, container, false)

        viewModel=ServicesViewModel(requireActivity().application)

        viewModel.getAllServices().observe(viewLifecycleOwner){
           val adapter=ServicesRecyclerView(it,this)

            val recyclerViewList=view.findViewById<RecyclerView>(R.id.recyclerViewList)

            recyclerViewList.adapter=adapter

            recyclerViewList.layoutManager=LinearLayoutManager(requireContext())
        }

        view.findViewById<FloatingActionButton>(R.id.floatingID).setOnClickListener {
            changeFragment(requireActivity().supportFragmentManager,CreateServiceFragment(),Bundle())
        }

        return view

    }

    override fun onDeleteItem(item: ServiceObject) {
        viewModel.deleteService(item)
    }
}