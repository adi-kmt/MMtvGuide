package com.example.mmtvguide.locations.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.LocationData
import com.example.mmtvguide.R
import com.example.mmtvguide.characters.adapter.CharacterListAdapter
import com.example.mmtvguide.common_adapters.ItemClickCallback
import com.example.mmtvguide.common_adapters.LoadingAdapter
import com.example.mmtvguide.databinding.FragmentLocationListBinding
import com.example.mmtvguide.locations.adapter.LocationListController
import com.example.mmtvguide.locations.adapter.LocationListadapter
import com.example.mmtvguide.locations.vm.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LocationListFragment : Fragment() {

    private var _binding:FragmentLocationListBinding?=null
    private val binding get() = _binding!!

    private lateinit var locationListAdapter: LocationListadapter
    private val locationViewModel:LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationListController = LocationListController()

//        locationListAdapter = LocationListadapter()

//        binding.locationRecyclerView.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = locationListAdapter
//                .withLoadStateHeaderAndFooter(
//                header = LoadingAdapter {locationListAdapter :: retry },
//                footer = LoadingAdapter {locationListAdapter :: retry }
//            )
//        }

        binding.locationRecycler.setController(locationListController)
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                locationViewModel.getLocation().collectLatest {locationData ->
//                    locationListAdapter.submitData(locationData)
                    Log.d("Location TAG", locationData.toString())
                    locationListController.submitData(locationData)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}