package com.example.barbershop.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.barbershop.data.objects.ServiceObject

@Dao
interface ServicesDao {
    @Transaction
    @Query("select * from services order by name")
    fun getAllServices():LiveData<List<ServiceObject>>

    @Transaction
    @Query("select * from services")
    fun getServices():List<ServiceObject>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj:ServiceObject)

    @Transaction
    @Delete
    fun delete(item: ServiceObject)
}