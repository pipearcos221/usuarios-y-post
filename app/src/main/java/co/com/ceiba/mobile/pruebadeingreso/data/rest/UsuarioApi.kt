package co.com.ceiba.mobile.pruebadeingreso.data.rest

import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.data.rest.Endpoints.GET_USERS
import io.reactivex.Flowable
import retrofit2.http.GET
import java.util.*

interface UsuarioApi {

    @GET(GET_USERS)
    fun getAll(): Flowable<List<User>>

}