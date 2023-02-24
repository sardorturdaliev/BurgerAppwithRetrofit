package com.sardordev.burgerapp.screens.detailscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sardordev.burgerapp.data.model.Ingredient
import com.sardordev.burgerapp.databinding.CardIngredientBinding

class IngredientAdapter : ListAdapter<Ingredient, IngredientAdapter.VH>(diff) {


    inner class VH(val binding: CardIngredientBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onbind(ingredient: Ingredient) {
            binding.tvIngredient.text = ingredient.name

            Glide.with(itemView.context).load(ingredient.img).into(binding.imgGradient)
        }

    }


    object diff : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        CardIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onbind(getItem(position))
    }
}