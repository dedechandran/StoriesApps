package com.dedechandran.storiesapps.presentation.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dedechandran.storiesapps.R
import com.dedechandran.storiesapps.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewLifecycleOwner) {
            vm.nameErrorMessage.observe(this) {
                binding.ilName.error = it
            }
            vm.emailErrorMessage.observe(this) {
                binding.ilEmail.error = it
            }
            vm.passwordErrorMessage.observe(this) {
                binding.ilPassword.error = it
            }
        }
    }
}