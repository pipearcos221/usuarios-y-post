package co.com.ceiba.mobile.pruebadeingreso.ui.main

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.ui.adapters.UserAdapter
import co.com.ceiba.mobile.pruebadeingreso.util.LifeDisposable


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val adapter = UserAdapter()
    private val dis = LifeDisposable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerViewSearchResults.adapter = adapter
    }


}