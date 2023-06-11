package com.example.app.ui.breed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.BreedItemBinding
import com.squareup.picasso.Picasso

class BreedListAdapter(private var breedList: List<String>) :
    RecyclerView.Adapter<BreedListAdapter.BreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val itemView = BreedItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BreedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = breedList[position]
        holder.bind(breed)
    }

    override fun getItemCount(): Int = breedList.size

    fun setList(list: List<String>) {
        if (breedList.isNullOrEmpty()) {
            breedList = list
        }
        notifyDataSetChanged()
    }
    class BreedViewHolder(private val itemBinding: BreedItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(breed: String) {
            val item = itemBinding.dogItem
            Picasso.get().load(breed).into(item)
        }
    }
}
