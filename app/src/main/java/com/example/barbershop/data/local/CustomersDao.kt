package com.example.barbershop.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.barbershop.data.objects.Customers

@Dao
interface CustomersDao {
    @Transaction
    @Query("select * from customers")
    fun getAllCustomers():List<Customers>


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(customer: Customers)

    @Transaction
    @Query("select * from customers where username=:username and password=:password")
    fun checkCustomer(username: String, password: String):LiveData<List<Customers>>

    @Transaction
    @Query("select * from customers")
    fun getAllCustomersLive():LiveData<List<Customers>>

}