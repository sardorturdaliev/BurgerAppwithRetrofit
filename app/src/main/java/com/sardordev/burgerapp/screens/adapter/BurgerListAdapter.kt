package com.sardordev.burgerapp.screens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sardordev.burgerapp.data.model.BurgerModelItem
import com.sardordev.burgerapp.databinding.CardBurgersBinding
import com.sardordev.burgerapp.utils.ClickItemListener

class BurgerListAdapter(val listener: ClickItemListener) :
    ListAdapter<BurgerModelItem, BurgerListAdapter.VH>(diff) {

    inner class VH(val binding: CardBurgersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onbind(burgerModelItem: BurgerModelItem) {
            binding.tvBurgername.text = burgerModelItem.name
            binding.tvCost.text = "$" + "${burgerModelItem.price.toString()}"

            Glide.with(itemView.context).load(burgerModelItem.images!![0]?.sm).into(binding.imgburgers)


            //click item by recyclerView
            itemView.setOnClickListener {
                listener.clickItem(burgerModelItem)
            }
        }
    }


    object diff : DiffUtil.ItemCallback<BurgerModelItem>() {
        override fun areItemsTheSame(oldItem: BurgerModelItem, newItem: BurgerModelItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BurgerModelItem,
            newItem: BurgerModelItem
        ): Boolean {
            return oldItem == newItem

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        CardBurgersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onbind(getItem(position))
    }


}