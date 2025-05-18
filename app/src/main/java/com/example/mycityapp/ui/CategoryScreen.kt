package com.example.mycityapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import com.example.mycityapp.model.PlaceCategory
import com.example.mycityapp.ui.theme.MyCityAppTheme

@Composable
fun CategoryList(
    categoryList: List<PlaceCategory>,
    onItemClick: (PlaceCategory) -> Unit = {},
    modifier: Modifier = Modifier,
){
    Text(
        text = stringResource(id = categoryList[0].categoryName),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier
            .padding(16.dp)
    )
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        items(categoryList.size) { index ->
            CategoryListItem(
                category = categoryList[index],
                onItemClick = onItemClick,
                modifier = modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun CategoryListItem(
    category: PlaceCategory,
    modifier: Modifier = Modifier,
    onItemClick: (PlaceCategory) -> Unit = {}
){
    // Calculate the width based on the length of the category name
    val widthDp = (stringResource(id = category.categoryName).length * 40)
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.small,
        onClick = { onItemClick(category) },
        modifier = modifier
    ){
        Box {
            Image(
                painter = painterResource(id = category.categoryImage),
                contentDescription = stringResource(id = category.categoryName),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(widthDp.dp)
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
                    text = stringResource(id = category.categoryName),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListItemPreview(){
    MyCityAppTheme {
        Surface {
            CategoryList(
                categoryList = LocalPlaceCategoryDataProvider.getCategoryList(),
            )
        }
    }
}