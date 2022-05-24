package com.example.barbershop.appointments

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.barbershop.*
import com.example.barbershop.data.objects.Customers
import com.example.barbershop.data.objects.ServiceObject
import com.example.barbershop.servicemanager.ServicesViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class AppointmentFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.appointment_fragment_new, container, false)

        val viewModel=ServicesViewModel(requireActivity().application)

        val autocomplete=view.findViewById<AutoCompleteTextView>(R.id.selectCustomer)

        var selectedCustomer:Customers ? =null

        val sharedPreferences=requireActivity().getSharedPreferences(
            Globals.APP_NAME,
            Context.MODE_PRIVATE
        )

        val userId = sharedPreferences.getInt(Globals.USER_ID,0)

        if(userId !=0){
            //hide the view
            view.findViewById<View>(R.id.selectCustomerView).visibility=View.GONE
        }

        viewModel.getAllCustomers().observe(viewLifecycleOwner){
            val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, it)

            autocomplete.setAdapter(adapter2)

            autocomplete.setOnItemClickListener { adapterView, _, position, _ ->
                val selected: Customers = adapterView.adapter.getItem(position) as Customers

                selectedCustomer = selected
            }

            for(i in it){
                if(i.id==userId){
                    selectedCustomer=i
                }
            }

        }

        view.findViewById<EditText>(R.id.selectDate).setOnClickListener {


            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

            datePicker.show(requireActivity().supportFragmentManager,"Select Date")

            datePicker.addOnPositiveButtonClickListener { selection: Long? ->
                val c = Calendar.getInstance()
                val date= Date(selection!!)

                c.time=date

                view.findViewById<EditText>(R.id.selectDate).setText(getString(R.string.string_date,c.get(
                    Calendar.YEAR),(c.get(Calendar.MONTH)+1),c.get(Calendar.DAY_OF_MONTH)))
            }
        }
        view.findViewById<EditText>(R.id.selectTime).setOnClickListener {
            TimePickerDialog(
                layoutInflater.context,
                { _, hourOfDay, minutes ->
                    view.findViewById<EditText>(R.id.selectTime).setText("$hourOfDay:$minutes")
                }, 0, 0, true
            ).show()
        }
        val spinner=view.findViewById<Spinner>(R.id.serviceSelect)

        viewModel.getAllServices().observe(viewLifecycleOwner){
            val spinnerAdapter: ArrayAdapter<ServiceObject> =
                ArrayAdapter<ServiceObject>(requireContext(), android.R.layout.simple_spinner_item, it)

            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = spinnerAdapter
        }

        view.findViewById<Button>(R.id.saveAppointment).setOnClickListener {

            if(selectedCustomer == null){
                showMessage("Please Select A customer!")
                return@setOnClickListener
            }
            val selectedService=spinner.selectedItem as ServiceObject

            val date=view.findViewById<EditText>(R.id.selectDate)

            val time=view.findViewById<EditText>(R.id.selectTime)

            if(date.text.toString().isNullOrEmpty()){
                showErrorDialog(requireContext(),"Please Select a date !")
                return@setOnClickListener
            }
            if(time.text.toString().isNullOrEmpty()){
                showErrorDialog(requireContext(),"Please Select time !")
                return@setOnClickListener
            }

            viewModel.saveAppointment(selectedCustomer!!.id,selectedCustomer!!.username,
                selectedService.id,
                selectedService.name,date.text.toString(),time.text.toString())


            showSuccessDialog(requireContext(),"Appointment Saved Successfully !!"){
                switchFragment(requireActivity().supportFragmentManager,R.id.frameDynamicLayout,Dashboard(),Bundle(),1)
            }

        }

        return view
    }
}