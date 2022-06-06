package com.example.mmtvguide.commonui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mmtvguide.common_adapters.ViewPagerAdapter
import com.example.mmtvguide.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment() {

    private var _binding:FragmentViewPagerBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        TabLayoutMediator(binding.MainTabLayout, binding.viewPager){tab, pos ->
            if(pos == 0){
                tab.text = "Characters"
            }
            else if(pos == 1){
                tab.text = "Locations"
            }
        }.attach()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}