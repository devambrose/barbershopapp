package com.example.barbershop.customers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.R
import com.example.barbershop.servicemanager.ServicesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CustomersDashBoard: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.appointment_fragment, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.customers_page)

        val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerViewList)

        val viewModel=ServicesViewModel(requireActivity().application)

        viewModel.getAllCustomers().observe(viewLifecycleOwner){

            recyclerView.layoutManager=LinearLayoutManager(requireContext())

            recyclerView.adapter=CustomersRecyclerView(it)
        }

        view.findViewById<FloatingActionButton>(R.id.floatingID).visibility=View.GONE

        return view
    }
}