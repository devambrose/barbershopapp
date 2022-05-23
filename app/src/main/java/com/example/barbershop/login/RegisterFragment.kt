package com.example.barbershop.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.barbershop.*
import com.example.barbershop.servicemanager.ServicesViewModel
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.activity_register_page, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.register_page)

        val usernameTextInputLayout=view.findViewById<TextInputLayout>(R.id.username)

        val phoneTextInputLayout=view.findViewById<TextInputLayout>(R.id.phonenumber)

        val passwordTextInputLayout=view.findViewById<TextInputLayout>(R.id.password)

        val confirmPasswordTextInputLayout=view.findViewById<TextInputLayout>(R.id.cpassword)


        view.findViewById<Button>(R.id.registerButton).setOnClickListener {

            if(usernameTextInputLayout.editText!!.text.isNullOrEmpty()){
                showErrorDialog(requireContext(),"Please Fill in all the Details")
                return@setOnClickListener
            }
            if(phoneTextInputLayout.editText!!.text.isNullOrEmpty()){
                showErrorDialog(requireContext(),"Please Fill in all the Details")
                return@setOnClickListener
            }
            if (passwordTextInputLayout.editText!!.text.isNullOrEmpty()){
                showErrorDialog(requireContext(),"Please Fill in all the Details")
                return@setOnClickListener
            }
            if(confirmPasswordTextInputLayout.editText!!.text.isNullOrEmpty()){
                showErrorDialog(requireContext(),"Please Fill in all the Details")
                return@setOnClickListener
            }

            val password=passwordTextInputLayout.editText!!.text.toString()

            val c_password=confirmPasswordTextInputLayout.editText!!.text.toString()

            val username=usernameTextInputLayout.editText!!.text.toString()

            val phone=phoneTextInputLayout.editText!!.text.toString()

            if(c_password != password){
                showErrorDialog(requireContext(),"Passwords Are not the same !")
                return@setOnClickListener
            }

            val viewModel=ServicesViewModel(requireActivity().application)

            viewModel.saveCustomer(username,phone,password)

            showSuccessDialog(requireContext(),"Registered Successfully !"){
                switchFragment(requireActivity().supportFragmentManager,R.id.frameLoginLayout,LoginFragment(),Bundle())

            }
        }

        return view

    }
}