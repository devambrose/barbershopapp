package com.example.barbershop.servicemanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.barbershop.data.local.LiveRoomDatabase
import com.example.barbershop.data.objects.Appointments
import com.example.barbershop.data.objects.Customers
import com.example.barbershop.data.objects.ServiceObject
import com.example.barbershop.getToday
import com.example.barbershop.showMessage
import java.lang.Exception

class ServicesViewModel(application: Application):AndroidViewModel(application) {

    private val database=LiveRoomDatabase.getDatabase(application.applicationContext)

    private val repository=ServicesRepository(database)

    fun getAllServices(): LiveData<List<ServiceObject>> {
        return repository.getAllServices()
    }

    fun saveCustomer(username: String, phone: String, password: String) {
        try {
            val data=repository.getAllCustomers()

            showMessage("customers $data")

            var id=1

            if(data.isNotEmpty()) id=data[data.size-1].id+1

             val customer= Customers(id,username,phone,password,getToday(),"","0")

             showMessage("customerData $customer")

             repository.saveCustomer(customer)

        }catch (io:Exception){
            showMessage("saveCustomer():ServicesViewModel->${io.message}")
        }
    }

    fun checkCredentials(username: String, password: String): LiveData<List<Customers>> {
        return repository.checkCustomer(username,password)
    }

    fun getAllCustomers(): LiveData<List<Customers>> {
        return repository.getAllCustomersLive()
    }

    fun saveService(name: String, cost: String) {
        try {

            var id=1

            val data=repository.getServices()

            if(data.isNotEmpty())
                id=data.size+1

            repository.saveService(ServiceObject(id,name,cost,1,0))
        }catch (io:Exception){
            showMessage("saveService():ServiceViewModel: ${io.message}")
        }
    }

    fun deleteService(item: ServiceObject) {
        repository.deleteItem(item)
    }

    fun getAppointments(): LiveData<List<Appointments>> {
        return repository.getAppointments()
    }

    fun saveAppointment(selectedCustomer: Int,customerName: String, product: Int,productName:String, date: String, time: String) {
        try {
            val data=repository.getAllAppointments()

            var id=1

            if(data.isNotEmpty())

                id=data.size+1

            showMessage("appointments $data")

            repository.saveAppointment(Appointments(id,selectedCustomer,date,time,0,0,product,customerName,productName))

        }catch (io:Exception){
            showMessage("appointments ${io.message}")
        }
    }

    fun getMyAppointments(userId: Int): LiveData<List<Appointments>> {
        return repository.getAppointmentsInt(userId)
    }

    fun updateAppointment(id: Int, i: Int) {
        repository.updateAppointment(id,i)
    }
}