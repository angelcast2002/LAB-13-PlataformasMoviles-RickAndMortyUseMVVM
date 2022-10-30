package com.example.lab8_plataformas.ui.logIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.lab8_plataformas.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LogInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkIsLogged()
        setObservables()
        setListeners()
    }

    private fun setListeners() {
        binding.buttonIniciarSesionLoginfragment.setOnClickListener{
            viewModel.checkUserAndPassword(
                binding.textInputCorreoTextLoginFragmentEditText.text.toString(),
                binding.textInputContrasenaTextLoginFragmentEditText.text.toString()
            )
        }
    }

    private fun setObservables() {
        lifecycleScope.launch {
            viewModel.logInStatus.collectLatest { state ->
                handleState(state)
            }
        }
    }

    private fun handleState(state: LogInUiStatus) {
        when(state) {

            LogInUiStatus.Default -> {
                binding.apply {
                    textInputCorreoTextLoginFragment.visibility = View.VISIBLE
                    textInputContrasenaTextLoginFragment.visibility = View.VISIBLE
                    buttonIniciarSesionLoginfragment.visibility = View.VISIBLE
                    progressLogIn.visibility = View.GONE
                }
            }
            is LogInUiStatus.Error -> {
                Toast.makeText(
                    requireContext(),
                    state.message,
                    Toast.LENGTH_LONG
                ).show()
                viewModel.setDefault()
            }
            LogInUiStatus.Loading -> {
                binding.apply {
                    textInputCorreoTextLoginFragment.visibility = View.GONE
                    textInputContrasenaTextLoginFragment.visibility = View.GONE
                    buttonIniciarSesionLoginfragment.visibility = View.GONE
                    progressLogIn.visibility = View.VISIBLE
                }
            }
            LogInUiStatus.Succes -> {
                logIn()
                viewModel.setDefault()
            }

        }
    }

    private fun logIn() {
        requireView().findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToPlaceListFragment()
        )
    }
}