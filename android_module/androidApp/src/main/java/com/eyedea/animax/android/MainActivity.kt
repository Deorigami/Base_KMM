package com.eyedea.animax.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eyedea.animax.android.databinding.ActivityMainBinding

class MainActivity(layout: Int = R.layout.activity_main) : AppCompatActivity(layout) {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, MainPage()).commit()
    }
}
