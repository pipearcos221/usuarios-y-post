package co.com.ceiba.mobile.pruebadeingreso.di

import android.arch.persistence.room.Room
import co.com.ceiba.mobile.pruebadeingreso.data.db.AppDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.rest.Endpoints.URL_BASE
import co.com.ceiba.mobile.pruebadeingreso.data.rest.PostApi
import co.com.ceiba.mobile.pruebadeingreso.data.rest.UserApi
import co.com.ceiba.mobile.pruebadeingreso.ui.main.MainViewModel
import co.com.ceiba.mobile.pruebadeingreso.ui.post.PostViewModel
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.ext.koin.androidApplication

import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val appModule: Module = module {

    viewModel<MainViewModel>()
    viewModel<PostViewModel>()

    single {
        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL_BASE)
                .build()
    }

    single<UserApi> {
        get<Retrofit>().create(UserApi::class.java)
    }

    single<PostApi> {
        get<Retrofit>().create(PostApi::class.java)
    }

    single {
        Room.databaseBuilder(
                androidApplication(),
                AppDatabase::class.java, "ceibadb"
        ).build()
    }

    single { get<AppDatabase>().userDao()}
}

