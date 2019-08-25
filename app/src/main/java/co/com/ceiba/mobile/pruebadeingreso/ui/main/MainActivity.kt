package co.com.ceiba.mobile.pruebadeingreso.ui.main

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle

import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding


class MainActivity : Activity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


}