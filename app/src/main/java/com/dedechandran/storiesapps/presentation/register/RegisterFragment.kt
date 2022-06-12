package com.dedechandran.storiesapps.presentation.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dedechandran.storiesapps.R
import com.dedechandran.storiesapps.common.hideSoftKeyboard
import com.dedechandran.storiesapps.common.setUserInteractionState
import com.dedechandran.storiesapps.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val vm : RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        _binding = FragmentRegisterBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = vm
        }
        binding.ivArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnRegister.setOnClickListener {
            vm.register()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewLifecycleOwner) {
            vm.name.errorMessage.observe(this) {
                binding.ilName.error = it
            }
            vm.email.errorMessage.observe(this) {
                binding.ilEmail.error = it
            }
            vm.password.errorMessage.observe(this) {
                binding.ilPassword.error = it
            }
            vm.isLoadingEvent.observe(this) {
                binding.divLoadingIndicator.root.isVisible = it
                requireActivity().window.setUserInteractionState(it)
                requireContext().hideSoftKeyboard(requireActivity().currentFocus)
                requireActivity().window.decorView.clearFocus()
            }
            vm.successfullyRegisterEvent.observe(this) {
                Snackbar.make(binding.root, "Successfully Register", Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
            vm.showErrorEvent.observe(this) {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}