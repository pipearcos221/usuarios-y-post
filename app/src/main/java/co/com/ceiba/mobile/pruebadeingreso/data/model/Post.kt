package co.com.ceiba.mobile.pruebadeingreso.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(@PrimaryKey val id: Int,
                @ColumnInfo(name = "user_id") val userId: Int,
                val title: String,
                val body: String): Parcelable