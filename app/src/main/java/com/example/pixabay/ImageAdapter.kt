package com.example.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixabay.databinding.ItemImageBinding

class ImageViewHolder(var binding: ItemImageBinding) : ViewHolder(binding.root){

    fun OnBind(image: ImageModel){
         binding.imageView.load(image.largeImageURL)
    }

}


class ImageAdapter(var list: ArrayList<ImageModel>): RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder = ImageViewHolder(ItemImageBinding
        .inflate(LayoutInflater
            .from(parent.context),
            parent,
            false))


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.OnBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

