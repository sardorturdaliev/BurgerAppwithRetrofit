package com.sardordev.burgerapp.screens.mainscreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sardordev.burgerapp.R
import com.sardordev.burgerapp.data.model.BurgerModelItem
import com.sardordev.burgerapp.databinding.FragmentMainBinding
import com.sardordev.burgerapp.objects.GetObjects
import com.sardordev.burgerapp.screens.adapter.BurgerListAdapter
import com.sardordev.burgerapp.utils.ClickItemListener
import com.sardordev.burgerapp.utils.UiEvent
import com.sardordev.burgerapp.viewmodel.BurgerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.net.InetAddress
import java.net.UnknownHostException

@AndroidEntryPoint
class MainFragment : Fragment(), ClickItemListener {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<BurgerViewModel>()
    private lateinit var burgerListAdapter: BurgerListAdapter
    private lateinit var internerConnectionCheck: InternerConnectionCheck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBurgersList()
        burgerListAdapter = BurgerListAdapter(this)
        internerConnectionCheck = InternerConnectionCheck(requireContext())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        internerConnectionCheck.observe(viewLifecycleOwner, Observer {
            if (it) {
//                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            } else {
//                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })


        binding.rvBurgers.apply {
            adapter = burgerListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launchWhenCreated {
            viewModel.burgerListObserver.collectLatest {

                when (it) {
                    UiEvent.Empty -> Unit
                    is UiEvent.Error -> {

                    }
                    UiEvent.Loading -> {
                        binding.progress.isVisible = true
                        binding.rvBurgers.isVisible = false
                    }
                    is UiEvent.Success<*> -> {
                        binding.progress.isVisible = false
                        binding.rvBurgers.isVisible = true
                        Log.d("TTT", "${it.data}")
                        val burgers = it.data as List<BurgerModelItem>
                        burgerListAdapter.submitList(burgers)
                    }

                }
            }
        }

    }



    override fun clickItem(burgerModelItem: BurgerModelItem) {
        GetObjects.burgerModelItem = burgerModelItem
        findNavController().navigate(R.id.detailFragment)
    }

}