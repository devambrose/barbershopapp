package com.example.barbershop.servicemanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.barbershop.R
import com.example.barbershop.showErrorDialog

class CreateServiceFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.services_new_fragment, container, false)

        val viewModel=ServicesViewModel(requireActivity().application)
        view.findViewById<Button>(R.id.saveService).setOnClickListener {

            val name=view.findViewById<EditText>(R.id.serviceName)

            val cost=view.findViewById<EditText>(R.id.serviceCost)

            if(name.text.toString().isNullOrEmpty()){
                showErrorDialog(requireContext(),"Please input a valid name!")
                return@setOnClickListener
            }
            if(cost.text.toString().isNullOrEmpty()){
                showErrorDialog(requireContext(),"Please input a valid cost!")
                return@setOnClickListener
            }

            viewModel.saveService(name.text.toString(),cost.text.toString())

            (name as TextView).text=""

            (cost as TextView).text=""

        }

        return view

    }
}