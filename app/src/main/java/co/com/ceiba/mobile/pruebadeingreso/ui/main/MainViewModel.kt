package co.com.ceiba.mobile.pruebadeingreso.ui.main

import android.arch.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.data.rest.UserApi
import co.com.ceiba.mobile.pruebadeingreso.util.applySchedulers
import io.reactivex.Flowable
import io.reactivex.Observable

class MainViewModel(val userApi: UserApi, val userDao: UserDao) : ViewModel() {

    fun getUserListLocal() : Flowable<List<User>> = userDao.getAllUsers().applySchedulers()

    fun getUserListRemote() : Observable<List<User>> = userApi.getAll().applySchedulers()

    fun insertUserListLocal(userList: List<User>) : Observable<List<User>> =  Observable
            .fromCallable { userDao.addUsers(userList) }
            .flatMap { Observable.just(userList) }
            .applySchedulers()


}