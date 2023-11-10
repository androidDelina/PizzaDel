package com.example.pizzadel.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pizzadel.databinding.FragmentHomeBinding
import com.example.pizzadel.pojo.Category
import com.example.pizzadel.ui.adapters.BannersAdapter
import com.example.pizzadel.ui.adapters.CategoriesListAdapter
import com.example.pizzadel.ui.adapters.CustomSpinnerAdapter
import com.example.pizzadel.ui.adapters.DessertListAdapter
import com.example.pizzadel.ui.adapters.PizzaListAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val spinner: Spinner = binding.actionBarSpinner

        val food_rv = binding.foodRv
        val pizzaAdapter = PizzaListAdapter()
        val dessertAdapter = DessertListAdapter()
        food_rv.adapter = pizzaAdapter

        val recyclerView = binding.categoryRv
        val categoriesAdapter = CategoriesListAdapter()
        categoriesAdapter.onCategoryClickListener = object :
            CategoriesListAdapter.OnCategoryClickListener {
            override fun onCategoryClick(category: Category) {
                homeViewModel.ternOnOffCategory(category)
                when (category.id) {
                    0 -> {
                        food_rv.adapter = pizzaAdapter
                    }
                    1 -> {
                        food_rv.adapter = dessertAdapter
                    }
                }
            }

        }

        homeViewModel.loadPizzaData()
        homeViewModel.loadDessertData()

        homeViewModel.getPizzaDataFromDb().observe(viewLifecycleOwner) {
            pizzaAdapter.submitList(it)
        }

        homeViewModel.getDessertDataFromDb().observe(viewLifecycleOwner) {
            dessertAdapter.submitList(it)
        }

        homeViewModel.citiesArray.observe(viewLifecycleOwner) {
            val adapter = CustomSpinnerAdapter(requireActivity(), it)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        requireActivity(),
                        "City: " + it[position],
                        Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }

        homeViewModel.bannersImgRes.observe(requireActivity()) {
            val recyclerView = binding.bannersRv
            recyclerView.adapter = BannersAdapter(it)
        }

        homeViewModel.categoriesList.observe(requireActivity()) {
            recyclerView.adapter = categoriesAdapter
            categoriesAdapter.submitList(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}