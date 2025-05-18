package com.example.mycityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class Place(
    @StringRes val placeName: Int,
    @DrawableRes val placeImage: Int,
    @StringRes val imageDescription: Int,
    @StringRes val placeLocation: Int,
    @StringRes val placeDetails: Int
)

data class PlaceCategory(
    @StringRes val categoryName: Int,
    @DrawableRes val categoryImage: Int,
    val recommendedPlaces: List<Place>
)