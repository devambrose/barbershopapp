package com.example.barbershop.servicemanager

import androidx.lifecycle.LiveData
import com.example.barbershop.data.local.LiveRoomDatabase
import com.example.barbershop.data.objects.Appointments
import com.example.barbershop.data.objects.Customers
import com.example.barbershop.data.objects.ServiceObject

class ServicesRepository(private val liveRoomDatabase: LiveRoomDatabase) {
    fun getAllServices(): LiveData<List<ServiceObject>> {
        return liveRoomDatabase.servicesDao().getAllServices()
    }

    fun getAllCustomers(): List<Customers> {
        return liveRoomDatabase.customersDao().getAllCustomers()
    }

    fun saveCustomer(customer: Customers) {
        liveRoomDatabase.customersDao().insert(customer)
    }

    fun checkCustomer(username: String, password: String): LiveData<List<Customers>> {
        return liveRoomDatabase.customersDao().checkCustomer(username,password)
    }

    fun getAllCustomersLive(): LiveData<List<Customers>> {
        return liveRoomDatabase.customersDao().getAllCustomersLive()
    }

    fun getServices(): List<ServiceObject> {
        return liveRoomDatabase.servicesDao().getServices()
    }

    fun saveService(serviceObject: ServiceObject) {
        liveRoomDatabase.servicesDao().insert(serviceObject)
    }

    fun deleteItem(item: ServiceObject) {
        liveRoomDatabase.servicesDao().delete(item)
    }

    fun getAppointments(): LiveData<List<Appointments>> {
        return liveRoomDatabase.appointmentsDao().getAllLive()
    }

    fun getAllAppointments()=
        liveRoomDatabase.appointmentsDao().getAll()

    fun saveAppointment(appointments: Appointments) {
        liveRoomDatabase.appointmentsDao().insert(appointments)
    }

    fun getAppointmentsInt(userId: Int): LiveData<List<Appointments>> {
        return liveRoomDatabase.appointmentsDao().getAppointmentsInt(userId)
    }

    fun updateAppointment(id: Int, i: Int) {
        liveRoomDatabase.appointmentsDao().update(id,i)
    }


}