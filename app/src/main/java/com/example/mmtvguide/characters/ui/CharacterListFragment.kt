package com.example.mmtvguide.characters.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.datamodels.mappers.toParcelizedCharachterData
import com.example.domain.model.CharachterData
import com.example.mmtvguide.R
import com.example.mmtvguide.characters.adapter.CharacterListAdapter
import com.example.mmtvguide.characters.vm.CharacterViewModel
import com.example.mmtvguide.common_adapters.ItemClickCallback
import com.example.mmtvguide.common_adapters.LoadingAdapter
import com.example.mmtvguide.commonui.ViewPagerFragmentDirections
import com.example.mmtvguide.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterListFragment : Fragment(), ItemClickCallback<CharachterData> {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var characterListAdapter: CharacterListAdapter
    private val charachterViewModel: CharacterViewModel by viewModels()

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
                    header = LoadingAdapter { characterListAdapter::retry },
                    footer = LoadingAdapter { characterListAdapter::retry }
                )
        }

        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                charachterViewModel.getCharacters().collectLatest { charachterData ->
                    characterListAdapter.submitData(charachterData)
                }
            }
        }
        setHasOptionsMenu(true)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                if (charachterViewModel.queryString.value != ""){
                    charachterViewModel.getFilteredCharacters().collectLatest {filteredData ->
                        characterListAdapter.retry()
                        characterListAdapter.submitData(filteredData)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search Character"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(requireContext(), query, Toast.LENGTH_LONG).show()
                query?.let { notNullQuery ->
                    charachterViewModel.setQuery(query = notNullQuery)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(Data: CharachterData) {
        val action =
            ViewPagerFragmentDirections.actionViewPagerFragmentToCharacterDetailFragment2(Data.toParcelizedCharachterData())
        findNavController().navigate(action)
    }
}