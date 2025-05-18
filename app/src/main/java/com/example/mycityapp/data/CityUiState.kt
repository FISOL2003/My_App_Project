package com.example.mycityapp.data

import com.example.mycityapp.model.Place
import com.example.mycityapp.model.PlaceCategory
import com.example.mycityapp.ui.ScreenState

data class CityUiState(
    val categoryList: List<PlaceCategory> = emptyList(),
    val currentCategory: PlaceCategory = LocalPlaceCategoryDataProvider.defaultCategory,
    val currentPlace: Place = currentCategory.recommendedPlaces.first(),
)


