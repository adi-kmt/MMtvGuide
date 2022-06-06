package com.example.mmtvguide.characters.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterListFragment : Fragment(), ItemClickCallback<CharachterData> {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var characterListAdapter: CharacterListAdapter
    private val charachterViewModel: CharacterViewModel by viewModels()

    private val listCharachterData = listOf(
        CharachterData("Mane", 1, "MMM", "Name", "Person", "Holder", "Holt", "main.tv"),
        CharachterData("Mane", 2, "MMM", "Namesake", "Person", "Holder", "Holt", "main.tv")
        )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterListAdapter = CharacterListAdapter(this)



        binding.characterRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
//            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterListAdapter
                .withLoadStateHeaderAndFooter(
                    header = LoadingAdapter { characterListAdapter::retry },
                    footer = LoadingAdapter { characterListAdapter::retry }
                )
        }

//        lifecycleScope.launchWhenCreated {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                charachterViewModel.getCharacters().collectLatest { charachterData ->
//                    characterListAdapter.submitData(charachterData)
//                }
//            }
//        }

        charachterViewModel.setQuery(null)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                charachterViewModel.getCharacters()
                charachterViewModel.listCharacters.collectLatest { charachterData ->
//                    characterListAdapter::retry
                    Log.d("TAG", charachterData.toString())
                    characterListAdapter.submitData(charachterData)
//                    characterListAdapter.submitList(listCharachterData)
                }
//            }
        }

        setHasOptionsMenu(true)

//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                if (charachterViewModel.queryString.value != "") {
////                    charachterViewModel.getFilteredCharacters().collectLatest { filteredData ->
////                        characterListAdapter.retry()
////                        characterListAdapter.submitData(filteredData)
//                    charachterViewModel.getFilteredCharacters()
//                    charachterViewModel.listCharacters.collectLatest { charachterData ->
//                        characterListAdapter.submitData(charachterData)
//                    }
//                }
//            }
//        }
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
//            charachterViewModel.getCharacters(query = query)
            if (query != null) {
                charachterViewModel.setQuery(query)
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