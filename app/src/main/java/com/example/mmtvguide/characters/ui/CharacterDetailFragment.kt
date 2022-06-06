package com.example.mmtvguide.characters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mmtvguide.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {

    private var _binding:FragmentCharacterDetailBinding?=null
    private val binding get() = _binding!!

    private val args by navArgs<CharacterDetailFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val charachterData = args.charachterData
        binding.apply {
            Glide.with(this@CharacterDetailFragment)
                .load(charachterData.image)
                .centerCrop()
                .into(characterImageView)
            genderTextView.text = charachterData.gender
            speciesTextView.text = charachterData.species
            typeTextView.text = charachterData.type
            statusTextView.text = charachterData.status
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}