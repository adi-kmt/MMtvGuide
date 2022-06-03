package com.example.mmtvguide.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.CharachterData
import com.example.mmtvguide.common_adapters.ItemClickCallback
import com.example.mmtvguide.databinding.CharacterItemBinding

class CharacterListAdapter(
    private val itemClickCallback: ItemClickCallback<CharachterData>
):PagingDataAdapter<CharachterData, CharacterListAdapter.CharacterListHolder>(DiffCall()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterListHolder(binding, itemClickCallback)
    }

    override fun onBindViewHolder(holder: CharacterListHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {item ->
            holder.bind(item)
        }
    }


    class CharacterListHolder(private val binding: CharacterItemBinding, private val itemClickCallback: ItemClickCallback<CharachterData>):RecyclerView.ViewHolder(binding.root){

        fun bind(charachterData: CharachterData){
            binding.apply {
                //bind image
                Glide.with(itemView)
                    .load(charachterData.image)
                    .centerCrop()
                    .into(characterImage)
                characterText.text = charachterData.name
            }
            itemView.setOnClickListener {
                itemClickCallback.onItemClick(charachterData)
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
