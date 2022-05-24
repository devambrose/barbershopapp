package com.example.barbershop.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.barbershop.*
import com.example.barbershop.servicemanager.ServicesViewModel
import com.google.android.material.textfield.TextInputLayout

class LoginFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.activity_login_page, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.login_page)

        val  sharedPreferences=requireActivity().getSharedPreferences(Globals.APP_NAME, Context.MODE_PRIVATE)


        val usernameTextInputLayout=view.findViewById<TextInputLayout>(R.id.textUsername)

        val passwordTextInputLayout=view.findViewById<TextInputLayout>(R.id.textpassword)


        view.findViewById<Button>(R.id.registerWithus).setOnClickListener {
            switchFragment(requireActivity().supportFragmentManager,R.id.frameLoginLayout,RegisterFragment(),Bundle())
        }

        val viewModel=ServicesViewModel(requireActivity().application)


        view.findViewById<Button>(R.id.loginButton).setOnClickListener {

            val editor: SharedPreferences.Editor = sharedPreferences.edit()

            val dialog= showProgressDialog(requireContext(),"Processing Login ") {

            }

            dialog.show()

            val password=passwordTextInputLayout.editText!!.text.toString()

            val username=usernameTextInputLayout.editText!!.text.toString()

            if(username.isNullOrEmpty()){
                showErrorDialog(requireContext()," Please fill in all the details !")
                return@setOnClickListener
            }
            if(password.isNullOrEmpty()){
                showErrorDialog(requireContext()," Please fill in all the details !")
                return@setOnClickListener
            }

            if((username.lowercase() =="admin" ) and (password.lowercase()=="1234")){

                dialog.hide()

                showMessage("response is admin")

                editor.putString(Globals.USER_NAME, "MR.Harvey Spector")

                editor.putInt(Globals.USER_ID, 0)
                editor.putBoolean(Globals.LOGGIN_STATUS, true)

                editor.apply()

                val intent= Intent(requireContext(),MainActivity::class.java)

                startActivity(intent)

                this.activity?.finish()
            }else{

                viewModel.checkCredentials(username,password).observe(viewLifecycleOwner){
                    val customers=it
                    if(it.isEmpty()){
                        dialog.hide()

                       showErrorDialog(requireContext(),"Unable To Login")
                    }else{
                        dialog.hide()

                        showSuccessDialog(requireContext(),"Welcome !"){

                            val user=customers[0]

                            editor.putString(Globals.USER_NAME, user.username)

                            editor.putInt(Globals.USER_ID, user.id)

                            editor.putBoolean(Globals.LOGGIN_STATUS, true)

                            editor.apply()

                            val intent= Intent(requireContext(),MainActivity::class.java)

                            startActivity(intent)

                            this.activity?.finish()
                        }
                    }
                }
            }




        }


        return view

    }
}