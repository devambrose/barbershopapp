package com.example.barbershop.data.objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "appointments")
data class Appointments (
    @PrimaryKey(autoGenerate = true)
    @Json(name="id") val id:Int,
    @Json(name="customer_id") val customer_id:Int,
    @Json(name="appointment_date") val appointment_date:String,
    @Json(name="appointment_time") val appointment_time:String,
    @Json(name="status") val status:Int=0,
    @Json(name="is_deleted") val is_deleted:Int=0,
    @Json(name="service") val service:Int=0,
    @Json(name="customer_name") val customerName:String="",
    @Json(name="product") val product:String=""
)