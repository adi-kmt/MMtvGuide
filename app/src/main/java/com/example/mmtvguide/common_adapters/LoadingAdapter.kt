package com.example.mmtvguide.common_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mmtvguide.databinding.ErrorStateBinding

class LoadingAdapter(
    private val retry: () -> Unit
)
    :LoadStateAdapter<LoadingAdapter.LoadStateViewHolder>() {
    class LoadStateViewHolder(private val binding: ErrorStateBinding,retry: () -> Unit): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryBtn.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {
            if(loadState is LoadState.Error){
                binding.errorTxt.isVisible = true
                binding.errorTxt.text = loadState.error.localizedMessage

            }

            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryBtn.isVisible = loadState !is LoadState.Loading
                errorTxt.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ErrorStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding, retry)
    }
}