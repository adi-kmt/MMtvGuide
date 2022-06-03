package com.example.mmtvguide.commonui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mmtvguide.common_adapters.ViewPagerAdapter
import com.example.mmtvguide.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.MainTabLayout, binding.viewPager){tab, pos ->
            if(pos == 0){
                tab.text = "Characters"
            }
            else if(pos == 1){
                tab.text = "Locations"
            }
        }.attach()
    }
}