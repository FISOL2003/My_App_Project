package com.example.mycityapp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycityapp.data.LocalPlaceCategoryDataProvider
import com.example.mycityapp.model.Place

@Composable
fun PlaceListAndDetails(
    place: Place,
    placeList: List<Place>,
    onItemClick: (place: Place) -> Unit = {},
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
    ){
        PlaceList(
            placeList = placeList,
            onItemClick = onItemClick,
            modifier = Modifier
                .weight(1f)
        )
        DetailItemScreen(
            place = place,
            modifier = Modifier
                .weight(2f)
        )
    }
}

@Preview
@Composable
fun Preview (){
    PlaceListAndDetails(
        place = LocalPlaceCategoryDataProvider.defaultCategory.recommendedPlaces.first(),
        placeList = LocalPlaceCategoryDataProvider.defaultCategory.recommendedPlaces

    )
}