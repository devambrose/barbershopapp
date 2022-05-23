package com.example.barbershop.data.objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "services")
data class ServiceObject(
    @PrimaryKey(autoGenerate = true)
    @Json(name="id") val id:Int,
    @Json(name="name") val name:String,
    @Json(name="price") val price:String,
    @Json(name="status") val status:Int=1,
    @Json(name="is_deleted") val deleted:Int=0
){
    override fun toString(): String {
        return this.name+"  @"+this.price
    }
}