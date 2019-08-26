package co.com.ceiba.mobile.pruebadeingreso.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(val id: Int,
                val userId: Int,
                val title: String,
                val body: String): Parcelable