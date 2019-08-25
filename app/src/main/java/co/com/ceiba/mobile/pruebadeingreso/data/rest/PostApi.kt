package co.com.ceiba.mobile.pruebadeingreso.data.rest

import co.com.ceiba.mobile.pruebadeingreso.data.model.Post
import co.com.ceiba.mobile.pruebadeingreso.data.rest.Endpoints.GET_POST_USER
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET(GET_POST_USER)
    fun getPostUser(@Query("userId") userId: Int): Flowable<List<Post>>

}