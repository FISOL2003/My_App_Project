package com.example.mycityapp.ui

import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.CityUiState
import com.example.mycityapp.data.LocalPlaceCategoryDataProvider
import com.example.mycityapp.model.Place
import com.example.mycityapp.model.PlaceCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(
        CityUiState(
            categoryList = LocalPlaceCategoryDataProvider.getCategoryList(),
            currentCategory = LocalPlaceCategoryDataProvider.getCategoryList().getOrElse(0) {
               LocalPlaceCategoryDataProvider.defaultCategory
            },
            currentPlace = LocalPlaceCategoryDataProvider.defaultCategory.recommendedPlaces.getOrElse(0) {
                LocalPlaceCategoryDataProvider.defaultCategory.recommendedPlaces.first()
            },
        )
    )
    val uiState: StateFlow<CityUiState> = _uiState



    fun updateCurrentCategory(selectedCategory: PlaceCategory) {
        _uiState.update {
            it.copy(currentCategory = selectedCategory)
        }
    }

    fun updateCurrentPlace(selectedPlace: Place) {
        _uiState.update {
            it.copy(currentPlace = selectedPlace)
        }
    }

    fun updateDetailsScreenStates(selectedPlace: Place) {
        _uiState.update {
            it.copy(
                currentPlace = selectedPlace
            )
        }
    }

    init {
        resetHomeScreenStates()
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentCategory = LocalPlaceCategoryDataProvider.defaultCategory,
                currentPlace = LocalPlaceCategoryDataProvider.defaultCategory.recommendedPlaces.first(),
            )
        }
    }
}
