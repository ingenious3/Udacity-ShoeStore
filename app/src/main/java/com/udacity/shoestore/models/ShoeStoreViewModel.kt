package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeStoreViewModel : ViewModel() {

    private val newDestination: MutableLiveData<Int> = MutableLiveData()
    private var shoeList: MutableLiveData<List<Shoe>> = MutableLiveData()
    private var shoeArrayList = ArrayList<Shoe>()
    var shoe: Shoe? = null

    init {
        //initialize the shoe with default values when viewModel get initialized
        shoe = Shoe("", 0, "", "")
    }

    //setter for shoe list that will be observed by ShoeList fragment
    private fun setShowList(listShoes: List<Shoe>) {
        shoeList.value = listShoes
    }

    // set logged-in destination
    fun setLoginDestination(navigateId: Int) {
        setNewDestination(navigateId)
    }

    // observable getter for shoe list
    fun getShowList(): LiveData<List<Shoe>> = shoeList

    // adding new shoe item to shoe's list
    // reset the shoe object for re-use
    fun addShoe(param_shoe: Shoe, destinationId: Int) {
        if (param_shoe.name.trim().isNotEmpty() || param_shoe.company.trim().isNotEmpty()
            || param_shoe.description.trim().isNotEmpty() || param_shoe.size > 0) {

            shoeArrayList.add(param_shoe)
            setShowList(shoeArrayList)
            setNewDestination(destinationId)
        }
        //reset the shoe for next shoe object
        shoe = Shoe("", 0, "", "")
    }

    // navigate back to the shoe list screen without clearing edittext
    fun cancel(destinationId: Int) {
        setLoginDestination(destinationId)
    }

    // on logout clear edittext
    fun clearShoe() {
        shoe = Shoe("", 0, "", "")
    }

    //used to set the navigation destination based on destinationId
    fun setNewDestination(destinationId: Int) {
        newDestination.value = destinationId
    }

    //observable for destination id
    fun getDestinationId(): LiveData<Int> = newDestination
}
