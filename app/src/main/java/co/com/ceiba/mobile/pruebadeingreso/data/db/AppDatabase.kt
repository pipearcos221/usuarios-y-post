package co.com.ceiba.mobile.pruebadeingreso.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post
import co.com.ceiba.mobile.pruebadeingreso.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}

