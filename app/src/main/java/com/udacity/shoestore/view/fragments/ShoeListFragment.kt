package com.udacity.shoestore.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.udacity.shoestore.R
import com.udacity.shoestore.adapters.ShoeListAdapter
import com.udacity.shoestore.models.ShoeStoreViewModel

class ShoeListFragment : Fragment() {

    private val TAG : String? = ShoeListFragment::class.simpleName
    private val viewModel: ShoeStoreViewModel by activityViewModels()
    private lateinit var binding: com.udacity.shoestore.databinding.FragmentShoeListBinding
    private lateinit var shoeListAdapter: ShoeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialize data-binding and inflate fragment layout
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shoe_list, null, false)

        //initialize navigation-controller
        with(binding) {
            shoestoreviewmodel = viewModel
            navDirection =
                ShoeListFragmentDirections.actionShoeListToShoeDetail()
        }

        shoeListAdapter = ShoeListAdapter(ArrayList())
        binding.shoeListRecyclerView.adapter = shoeListAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getShowList().observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.shoeListRecyclerView
                Log.d(TAG, it.toString())
                shoeListAdapter.setShoeList(it)
            }
        })
    }
}