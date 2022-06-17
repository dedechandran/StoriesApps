package com.dedechandran.storiesapps.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dedechandran.storiesapps.R
import com.dedechandran.storiesapps.databinding.StoryItemBinding

class StoryAdapter : ListAdapter<StoryItem, StoryAdapter.StoryViewHolder>(DIFF_UTIL) {

    private var onItemClickListener: ((String) -> Unit)? = null

    inner class StoryViewHolder(
        val binding: StoryItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoryItem) {
            with(binding) {
                ivStory.load(item.image)
                tvDateTime.text = item.dateTime
                tvDescription.text = item.description
                root.setOnClickListener {
                    onItemClickListener?.invoke(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StoryViewHolder(
            DataBindingUtil.inflate(inflater, R.layout.story_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnItemClickListener(onItemClickListener: (String) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<StoryItem>() {
            override fun areItemsTheSame(oldItem: StoryItem, newItem: StoryItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StoryItem, newItem: StoryItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}