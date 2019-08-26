package co.com.ceiba.mobile.pruebadeingreso.data.rest

import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.data.rest.Endpoints.GET_USERS
import io.reactivex.Observable
import retrofit2.http.GET

interface UserApi {

    @GET(GET_USERS)
    fun getAll(): Observable<List<User>>

}