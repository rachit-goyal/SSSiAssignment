package com.learn.sssiassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learn.sssiassignment.data.remote.models.UserDataX
import com.learn.sssiassignment.databinding.SingleItemBinding
import com.learn.sssiassignment.utils.cons.ApiConstants

/**
created by Rachit on 3/27/2024.
 */
class RvAdapter(
    private var userData: List<UserDataX>,
    private var itemsIds:List<String>,
    private var onItemClicked: ((data: UserDataX) -> Unit),

    ) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

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
                binding.del.visibility=View.GONE
                binding.name.text = this.name
                binding.coins.text = this.coins
                binding.language.text = this.languageName
                if (this.profilePic != null && this.profilePic.isEmpty().not()) {
                    Glide
                        .with(binding.img.context)
                        .load(ApiConstants.BASE_URL + this.profilePic.substring(1))
                        .centerCrop()
                        .into(binding.img)
                }

                binding.fav.setOnClickListener {
                    onItemClicked(userData[position])
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