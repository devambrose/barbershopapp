package com.example.barbershop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.barbershop.data.objects.Appointments
import com.example.barbershop.data.objects.Customers
import com.example.barbershop.data.objects.ServiceObject

@Database(entities = [ServiceObject::class,Appointments::class,Customers::class], version = 4, exportSchema = false)
abstract class LiveRoomDatabase: RoomDatabase() {

    abstract fun servicesDao():ServicesDao
    abstract fun customersDao(): CustomersDao
    abstract fun appointmentsDao(): AppointmentsDao

    companion object {
        private var INSTANCE :LiveRoomDatabase ?=null

        fun getDatabase(context: Context): LiveRoomDatabase {
            return  INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LiveRoomDatabase::class.java,
                    "barber"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance

            }

        }


    }
}