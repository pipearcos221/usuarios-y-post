package co.com.ceiba.mobile.pruebadeingreso

import android.app.Application
import co.com.ceiba.mobile.pruebadeingreso.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }

    }

}