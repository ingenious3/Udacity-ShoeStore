package com.udacity.shoestore.view.fragments

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShowDetailBinding
import com.udacity.shoestore.models.ShoeStoreViewModel

class ShoeDetailFragment() : Fragment() {
    private val viewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialize data-binding and inflate fragment layout
        val binding: FragmentShowDetailBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_show_detail, null, false)

        //initialize navigation-controller
        with(binding) {
            showstoreviewmodel = viewModel
            navDirection =
                ShoeDetailFragmentDirections.actionShoeDetailToShoeList()
        }
        return binding.root
    }
}