package com.learn.sssiassignment.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.sssiassignment.databinding.SingleItemBinding
import com.learn.sssiassignment.data.local.UserLocalModel
import com.learn.sssiassignment.data.remote.models.UserDataX

/**
created by Rachit on 3/28/2024.
 */
class RvLocalAdapter(
    private var userData: List<UserLocalModel>,
    private var onItemClicked: ((id: Int) -> Unit),

    ) : RecyclerView.Adapter<RvLocalAdapter.ViewHolder>() {

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // bind the items with each item
    // of the list languageList
    // which than will be
    // shown in recycler view
    // to keep it simple we are
    // not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(userData[position]) {
                binding.fav.visibility=View.GONE

                binding.name.text = this.name
                binding.coins.text = this.coins
                binding.language.text = this.languageName
                if (this.profilePic != null && this.profilePic.isEmpty().not()) {
                    binding.img.setImageBitmap(
                        BitmapFactory.decodeByteArray(this.profilePic, 0, this.profilePic.size)
                    )
                }
                binding.del.setOnClickListener {
                    onItemClicked(userData[position].uId!!)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return userData.size
    }
}