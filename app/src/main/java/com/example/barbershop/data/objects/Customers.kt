package com.example.barbershop.data.objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "customers")
data class Customers(
    @PrimaryKey(autoGenerate = true)
    @Json(name="id") val id:Int,
    @Json(name="username") val username:String,
    @Json(name="gender") val gender:String,
    @Json(name="password") val password:String,
    @Json(name="created_on") val created_on:String,
    @Json(name="created_at") val created_at: String,
    @Json(name="flag") val flag:String,
){
    override fun toString(): String {
        return this.username
    }
}