package co.com.ceiba.mobile.pruebadeingreso.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers():Flowable<List<User>>

    @Insert
    fun addUsers(userList: List<User>)

}