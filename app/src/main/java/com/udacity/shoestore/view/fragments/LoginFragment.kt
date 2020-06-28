package com.udacity.shoestore.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.models.ShoeStoreViewModel

class LoginFragment : Fragment() {

    private val viewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialize data-binding and inflate fragment layout
        val binding: com.udacity.shoestore.databinding.FragmentLoginBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, null, false)

        //initialize navigation-controller
        with(binding) {
            shoestoreviewmodel = viewModel
            navDirection =
                LoginFragmentDirections.actionLoginToWelcome()
        }
        return binding.root
    }

}