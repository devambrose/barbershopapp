package com.example.barbershop.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.barbershop.data.objects.Appointments

@Dao
interface AppointmentsDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(appointments: Appointments)

    @Transaction
    @Query("select appointments.*, customers.username customer_name  from appointments left join customers on customers.id=appointments.customer_id order by appointment_date,appointment_time")
    fun getAllLive():LiveData<List<Appointments>>

    @Transaction
    @Query("select * from appointments")
    fun getAll():List<Appointments>

    @Transaction
    @Query("select * from appointments where status !=3 and customer_id=:userId order by id desc")
    fun getAppointmentsInt(userId: Int):LiveData<List<Appointments>>

    @Transaction
    @Query("update appointments set status=:i where id=:id")
    fun update(id: Int, i: Int)
}
/** @PrimaryKey(autoGenerate = true)
@Json(name="id") val id:Int,
@Json(name="customer_id") val customer_id:Int,
@Json(name="appointment_date") val appointment_date:String,
@Json(name="appointment_time") val appointment_time:String,
@Json(name="status") val status:Int=0,
@Json(name="is_deleted") val is_deleted:Int=0,
@Json(name="service") val service:Int=0,
@Json(name="customer_name") val customerName:String="",
@Json(name="product") val product:String=""*/