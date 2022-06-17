package com.dedechandran.storiesapps.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.dedechandran.storiesapps.R
import com.dedechandran.storiesapps.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewLifecycleOwner) {
            vm.storyDisplayedItems.observe(this) {
                binding.rvStories.setItems(it)
                binding.ivEmptyStory.isVisible = it.isEmpty()
            }
            vm.isLoadingEvent.observe(this) {
                binding.progressBar.isVisible = it
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getStories()
    }
}