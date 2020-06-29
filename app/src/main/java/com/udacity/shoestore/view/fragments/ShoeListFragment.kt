package com.udacity.shoestore.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.models.ShoeStoreViewModel

class ShoeListFragment : Fragment() {

    private val TAG : String? = ShoeListFragment::class.simpleName
    private val viewModel: ShoeStoreViewModel by activityViewModels()
    private lateinit var binding: com.udacity.shoestore.databinding.FragmentShoeListBinding
    private var menuItemLogout: MenuItem? = null

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

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getShowList().observe(viewLifecycleOwner, Observer {
            it.forEachIndexed { index, shoe ->
                val textView = TextView(requireContext())
                textView.text = "\n \n Name: ${shoe.name} \n Company: ${shoe.company} \n Size: ${shoe.size} \n Description: ${shoe.description}"
                textView.id = index
                textView.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                binding.shoeLists.addView(textView)
            }
        })
    }

    // setup options-menu
    override fun onCreateOptionsMenu(menu: Menu, menuInflater : MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.menu, menu)

        menuItemLogout = menu!!.getItem(0)
        menuItemLogout?.isVisible = true

    }

    // setup options-menu click event
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.clearShoe()
        return NavigationUI.onNavDestinationSelected(item!!, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}