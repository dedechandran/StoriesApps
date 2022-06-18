package com.dedechandran.storiesapps.presentation.addstory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dedechandran.storiesapps.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_story, container, false)
    }

}