package com.dedechandran.storiesapps.presentation.home

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StoryCardList @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context,attributeSet,defStyleAttr) {

    private val storyAdapter: StoryAdapter by lazy {
        StoryAdapter()
    }

    init {
        super.setAdapter(storyAdapter)
        super.setLayoutManager(LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false))
    }


    fun setOnItemClickListener(onItemClickListener: (String) -> Unit) {
        storyAdapter.setOnItemClickListener(onItemClickListener)
    }

    fun setItems(items: List<StoryItem>){
        storyAdapter.submitList(items)
    }

}