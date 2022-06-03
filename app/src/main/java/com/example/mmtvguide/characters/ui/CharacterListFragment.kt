package com.example.mmtvguide.characters.ui

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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.datamodels.mappers.toParcelizedCharachterData
import com.example.domain.model.CharachterData
import com.example.mmtvguide.R
import com.example.mmtvguide.characters.adapter.CharacterListAdapter
import com.example.mmtvguide.characters.vm.CharacterViewModel
import com.example.mmtvguide.common_adapters.ItemClickCallback
import com.example.mmtvguide.common_adapters.LoadingAdapter
import com.example.mmtvguide.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterListFragment : Fragment(), ItemClickCallback<CharachterData> {

    private var _binding: FragmentCharacterListBinding?=null
    private val binding get() = _binding!!

    private lateinit var characterListAdapter: CharacterListAdapter
    private val charachterViewModel:CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterListAdapter = CharacterListAdapter(this)

        binding.characterRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = characterListAdapter
                .withLoadStateHeaderAndFooter(
                header = LoadingAdapter {characterListAdapter :: retry },
                footer = LoadingAdapter {characterListAdapter :: retry }
            )
        }

        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                charachterViewModel.getCharacters().collectLatest {charachterData ->
                    characterListAdapter.submitData(charachterData)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(Data: CharachterData) {
        Log.e("Clicked Item", Data.toString())
        val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(Data.toParcelizedCharachterData())
        requireActivity().findNavController(R.id.fragmentContainerView).navigate(action)
    }
}