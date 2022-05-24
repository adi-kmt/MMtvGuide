package com.example.mmtvguide.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CharachterData
import com.example.mmtvguide.databinding.CharacterItemBinding

class CharacterListAdapter:ListAdapter<CharachterData, CharacterListAdapter.CharacterListHolder>(DiffCall()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterListHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    class CharacterListHolder(private val binding: CharacterItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(charachterData: CharachterData){
            binding.apply {
                //bind image
                characterText.text = charachterData.name
            }
        }
    }
    class DiffCall:DiffUtil.ItemCallback<CharachterData>() {
        override fun areItemsTheSame(oldItem: CharachterData, newItem: CharachterData): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CharachterData, newItem: CharachterData): Boolean =
            oldItem == newItem
    }
}
