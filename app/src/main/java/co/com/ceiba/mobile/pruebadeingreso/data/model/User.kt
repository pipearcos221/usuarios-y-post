package co.com.ceiba.mobile.pruebadeingreso.data.model

import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (@PrimaryKey val id: Int,
                 val name: String,
                 val email: String,
                 val phone: String) : Parcelable
