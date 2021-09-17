package com.syntia.moviecatalogue.features.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.syntia.moviecatalogue.core.utils.remove
import com.syntia.moviecatalogue.core.utils.show
import com.syntia.moviecatalogue.databinding.LayoutLoadStateItemBinding

class PagingLoadStateAdapter(private val onErrorFetch: (Throwable) -> Unit) :
    LoadStateAdapter<PagingLoadStateAdapter.PagingLoadStateViewHolder>() {

  override fun onBindViewHolder(holder: PagingLoadStateViewHolder, loadState: LoadState) {
    holder.bind(loadState)
  }

  override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingLoadStateViewHolder {
    return PagingLoadStateViewHolder(
        LayoutLoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent,
            false))
  }

  inner class PagingLoadStateViewHolder(private val binding: LayoutLoadStateItemBinding) :
      RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
      binding.apply {
        when (loadState) {
          is LoadState.Loading -> spinKitItemLoad.show()
          is LoadState.Error -> {
            spinKitItemLoad.remove()
            onErrorFetch.invoke(loadState.error)
          }
          else -> {
          }
        }
      }
    }
  }
}