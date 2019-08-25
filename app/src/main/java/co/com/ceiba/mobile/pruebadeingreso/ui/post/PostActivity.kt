package co.com.ceiba.mobile.pruebadeingreso.ui.post

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle

import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding

class PostActivity : Activity() {

    private lateinit var binding : ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)
    }

}
