package co.com.ceiba.mobile.pruebadeingreso.di

import co.com.ceiba.mobile.pruebadeingreso.data.rest.Endpoints
import co.com.ceiba.mobile.pruebadeingreso.data.rest.Endpoints.URL_BASE
import co.com.ceiba.mobile.pruebadeingreso.data.rest.PostApi
import co.com.ceiba.mobile.pruebadeingreso.data.rest.UsuarioApi
import co.com.ceiba.mobile.pruebadeingreso.ui.main.MainViewModel
import co.com.ceiba.mobile.pruebadeingreso.ui.post.PostViewModel
import org.koin.android.experimental.dsl.viewModel

import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val appModule: Module = module {

    viewModel<MainViewModel>()
    viewModel<PostViewModel>()

    single{
        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL_BASE)
                .build()
    }

    single<UsuarioApi> {
        get<Retrofit>().create(UsuarioApi::class.java)
    }

    single<PostApi> {
        get<Retrofit>().create(PostApi::class.java)
    }

}

