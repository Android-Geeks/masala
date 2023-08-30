package com.example.masala_food_recipes.ui.fragment

import androidx.navigation.Navigation
import com.example.masala_food_recipes.data.interactors.Cuisines
import com.example.masala_food_recipes.data.util.Constants
import com.example.masala_food_recipes.data.util.PreferencesUtil
import com.example.masala_food_recipes.databinding.FragmentCuisineDetailsBinding
import com.example.masala_food_recipes.ui.recyclerview.CuisineDetailsAdapter

class CuisineDetailsFragment :
    BaseFragment<FragmentCuisineDetailsBinding>(FragmentCuisineDetailsBinding::inflate) {
    override fun onCreateView() {

        val cuisineFromHome = PreferencesUtil.getFromSharedPref(Constants.CUISINE_KEY)

        if (cuisineFromHome != null) {
            buildingFragmentContent(cuisineFromHome)
        }
    }

    private fun buildingFragmentContent(cuisineName : String) {

        val recipes = viewModel.recipes
        val cuisinesList by lazy { Cuisines(recipes).getCuisineDetails(cuisineName) }

        binding.apply {
            cuisineType.title = cuisineName
            cuisineRecyclerFragment.adapter = CuisineDetailsAdapter(cuisinesList , recipes)
            cuisineType.setNavigationOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
        }
    }
}