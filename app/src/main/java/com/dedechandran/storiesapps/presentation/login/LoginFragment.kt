package com.dedechandran.storiesapps.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dedechandran.storiesapps.R
import com.dedechandran.storiesapps.common.hideSoftKeyboard
import com.dedechandran.storiesapps.common.setUserInteractionState
import com.dedechandran.storiesapps.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val vm: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        _binding = FragmentLoginBinding.bind(view).apply {
            viewModel = vm
            lifecycleOwner = viewLifecycleOwner
        }
        binding.btnLogin.setOnClickListener {
            vm.login()
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewLifecycleOwner) {
            vm.showErrorEvent.observe(this) {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
            vm.isLoadingEvent.observe(this) {
                binding.divLoadingIndicator.root.isVisible = it
                requireActivity().window.setUserInteractionState(it)
                requireContext().hideSoftKeyboard(requireActivity().currentFocus)
                requireActivity().window.decorView.clearFocus()
            }
        }
    }






}