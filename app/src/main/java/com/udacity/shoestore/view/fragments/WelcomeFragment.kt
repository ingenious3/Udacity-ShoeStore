package com.udacity.shoestore.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.models.ShoeStoreViewModel

class WelcomeFragment : Fragment() {
    private val viewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // initialize data-binding and inflate fragment layout
        val binding: com.udacity.shoestore.databinding.FragmentWelcomeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_welcome, null, false)

        //initialize navigation-controller
        with(binding) {
            viewmodel = viewModel
            navDirection =
                WelcomeFragmentDirections.actionWelcomeToInstructions()
        }
        return binding.root
    }
}