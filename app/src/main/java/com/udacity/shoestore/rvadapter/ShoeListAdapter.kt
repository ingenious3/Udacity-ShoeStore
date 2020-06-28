package com.udacity.shoestore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListAdapter(shoes: ArrayList<Shoe>) : RecyclerView.Adapter<ShoeListAdapter.ShoeViewHolder>() {
    var shoeArrayList: List<Shoe> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ShoeViewHolder {
        val shoeListItemBinding: ShoeListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.shoe_list_item, parent, false
        )
        return ShoeViewHolder(shoeListItemBinding)
    }

    override fun getItemCount() = shoeArrayList.size

    override fun onBindViewHolder(shoeViewHolder: ShoeViewHolder, position: Int) {
        shoeViewHolder.shoeListItemBinding.shoe = shoeArrayList[position]
    }

    //set the shoe's list
    fun setShoeList(shoes: List<Shoe>) {
        shoeArrayList = shoes

        //and notify the change to re-render the list whenever the list get updated
        notifyDataSetChanged()
    }

    class ShoeViewHolder(val shoeListItemBinding: ShoeListItemBinding) :
        RecyclerView.ViewHolder(shoeListItemBinding.root) {
    }

}