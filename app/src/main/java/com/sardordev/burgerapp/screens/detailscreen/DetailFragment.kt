package com.sardordev.burgerapp.screens.detailscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sardordev.burgerapp.R
import com.sardordev.burgerapp.databinding.FragmentDetailBinding
import com.sardordev.burgerapp.objects.GetObjects
import com.sardordev.burgerapp.screens.detailscreen.adapter.IngredientAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private lateinit var ingredientAdapter: IngredientAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initData()
        initIngredient()

        return binding.root
    }

    private fun initData() {
        binding.tvDetailName.text = GetObjects.burgerModelItem.name

        Glide.with(requireContext()).load(GetObjects.burgerModelItem.images!![0]?.sm)
            .into(binding.detailImageView)

        binding.tvdesriptionBurger.text = GetObjects.burgerModelItem.desc

    }


    private fun initIngredient() {
        ingredientAdapter = IngredientAdapter()
        binding.rvIngredient.apply {
            adapter = ingredientAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        val list = GetObjects.burgerModelItem.ingredients

        Log.d("III", list.toString())

        ingredientAdapter.submitList(list)

    }


}