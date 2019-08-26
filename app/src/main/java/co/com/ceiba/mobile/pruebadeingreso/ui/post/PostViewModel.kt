package co.com.ceiba.mobile.pruebadeingreso.ui.post

import android.arch.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post
import co.com.ceiba.mobile.pruebadeingreso.data.rest.PostApi
import co.com.ceiba.mobile.pruebadeingreso.util.applySchedulers
import io.reactivex.Observable

class PostViewModel(val postApi: PostApi) : ViewModel() {

    fun getUserPostRemote(userId: Int) : Observable<List<Post>> = postApi.getPostUser(userId).applySchedulers()

}