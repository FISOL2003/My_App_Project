package com.example.mycityapp.data

import com.example.mycityapp.R
import com.example.mycityapp.data.DataSource.HotelPlace
import com.example.mycityapp.data.DataSource.MallsPlace
import com.example.mycityapp.data.DataSource.RestaurantsPlace
import com.example.mycityapp.data.DataSource.TemplePlace
import com.example.mycityapp.model.PlaceCategory


object LocalPlaceCategoryDataProvider {
    val defaultCategory = getCategoryList().first()
    fun getCategoryList(): List<PlaceCategory> {
        return listOf(
            PlaceCategory(
                categoryName = R.string.restaurants,
                categoryImage = R.drawable.restaurant_6479818_1280,
                recommendedPlaces = RestaurantsPlace
            ),
            PlaceCategory(
                categoryName = R.string.malls,
                categoryImage = R.drawable.malls_picture,
                recommendedPlaces = MallsPlace
            ),
            PlaceCategory(
                categoryName = R.string.temple,
                categoryImage = R.drawable.angkor_wat_2540898_1280,
                recommendedPlaces = TemplePlace
            ),
            PlaceCategory(
                categoryName = R.string.hotels,
                categoryImage = R.drawable.hotel_1979406_1280,
                recommendedPlaces = HotelPlace
            ),
        )
    }
}
