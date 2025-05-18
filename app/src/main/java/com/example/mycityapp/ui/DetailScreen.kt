package com.example.mycityapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.R
import com.example.mycityapp.model.Place
import com.example.mycityapp.ui.theme.MyCityAppTheme

@Composable
fun DetailItemScreen(
    place: Place,
    modifier: Modifier = Modifier
){
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(state = scrollState)
    ){
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier
                .padding(16.dp)
                .height(200.dp),
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        ){
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){
                Image(
                    painter = painterResource(id = place.placeImage),
                    contentDescription = stringResource(id = place.placeName),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            TextName(
                text = stringResource(id = place.placeName)
            )
            TextLocation(
                text = stringResource(id = place.placeLocation)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            TextDetails(
                text = stringResource(id = place.placeDetails),
            )
        }
    }
}

@Composable
fun TextName(
 text: String,
){
    Text(
        text = text,
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = 30.sp,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    )
}

@Composable
fun TextLocation(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = stringResource(R.string.location),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}

@Composable
fun TextDetails(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = stringResource(R.string.about),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
            .fillMaxSize()
    )
}

@Preview
@Composable
fun DetailScreenPreview(){
    val viewModel: CityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    MyCityAppTheme{
        Surface {
            DetailItemScreen(
                place = uiState.currentPlace
            )
        }
    }
}