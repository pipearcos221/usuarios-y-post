package co.com.ceiba.mobile.pruebadeingreso.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post
import io.reactivex.Flowable

@Dao
interface PostDao {

    @Insert
    fun addPost(postList: List<Post>)

    @Query("SELECT * FROM post WHERE user_id = :userId")
    fun getUserPost(userId: Int): Flowable<List<Post>>

}