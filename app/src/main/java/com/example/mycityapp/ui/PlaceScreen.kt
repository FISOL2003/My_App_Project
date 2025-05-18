package com.example.mycityapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.LocalPlaceCategoryDataProvider
import com.example.mycityapp.model.Place
import com.example.mycityapp.ui.theme.MyCityAppTheme

@Composable
fun PlaceList(
    placeList: List<Place>,
    onItemClick: (Place) -> Unit = {},
    modifier: Modifier = Modifier,
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(placeList.size) {
            PlaceListItem(
                place = placeList[it],
                onItemClick = onItemClick,
            )
        }
    }
}

@Composable
fun PlaceListItem(
    place: Place,
    onItemClick: (place: Place) -> Unit = {},
    modifier: Modifier = Modifier,
){
    Card(
        onClick = {
            onItemClick(place)
        },
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxSize()
        ){
            Image(
                painter = painterResource(id = place.placeImage),
                contentDescription = stringResource(id = place.placeName),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.extraSmall
                    )
                    .align(Alignment.BottomStart)
                    .padding(start = 4.dp, end = 4.dp)
            ){
                Text(
                    text = stringResource(id = place.placeName),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PlaceListItemPreview(){
    MyCityAppTheme {
        Surface {
            PlaceList(
                placeList = LocalPlaceCategoryDataProvider.defaultCategory.recommendedPlaces
            )
        }
    }
}