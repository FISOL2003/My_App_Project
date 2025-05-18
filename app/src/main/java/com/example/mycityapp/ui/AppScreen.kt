package com.example.mycityapp.ui

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycityapp.R
import com.example.mycityapp.utils.PlaceContentType

enum class ScreenState(@StringRes val title: Int) {
    CATEGORY_SCREEN(title = R.string.category_screen),
    PLACE_SCREEN(title = R.string.place_screen),
    DETAIL_SCREEN(title = R.string.detail_screen)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val navController: NavHostController = rememberNavController()
        val viewModel: CityViewModel = viewModel()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = ScreenState.valueOf(
            backStackEntry?.destination?.route ?: ScreenState.CATEGORY_SCREEN.name
        )
        val navigationType: PlaceContentType = when (windowSize) {
            WindowWidthSizeClass.Compact -> {
                PlaceContentType.ListOnly
            }
            WindowWidthSizeClass.Expanded -> {
                PlaceContentType.ListAndDetail
            }
            else -> {
                PlaceContentType.ListOnly
            }
        }
        Scaffold(
            topBar = {
                AppTopbar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ){ innerPadding ->
            val uiState by viewModel.uiState.collectAsState()
            NavHost(
                navController = navController,
                startDestination = ScreenState.CATEGORY_SCREEN.name,
                modifier = Modifier.padding(innerPadding),
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -300 },
                        animationSpec = tween(
                            300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -300 },
                        animationSpec = tween(
                            300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))

                },
                enterTransition =  {
                    slideInHorizontally(
                        initialOffsetX =  { 300 },
                        animationSpec = tween(
                            300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { 300 },
                        animationSpec = tween(
                            300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))

                }
            ) {
                composable(route = ScreenState.CATEGORY_SCREEN.name) {
                    CategoryList(
                        categoryList = uiState.categoryList,
                        onItemClick = {
                            viewModel.updateCurrentCategory(it)
                            viewModel.updateDetailsScreenStates(it.recommendedPlaces.first())
                            navController.navigate(ScreenState.PLACE_SCREEN.name)
                        }
                    )
                }
                if (navigationType == PlaceContentType.ListAndDetail) {
                    composable(route = ScreenState.PLACE_SCREEN.name) {
                        PlaceListAndDetails(
                            place = uiState.currentPlace,
                            placeList = uiState.currentCategory.recommendedPlaces,
                            onItemClick = {
                                viewModel.updateDetailsScreenStates(it)
                                viewModel.updateCurrentPlace(it)
                            }
                        )
                    }
                } else{
                    composable( route = ScreenState.PLACE_SCREEN.name ) {
                        PlaceList(
                            placeList = uiState.currentCategory.recommendedPlaces,
                            onItemClick = {
                                viewModel.updateDetailsScreenStates(it)
                                viewModel.updateCurrentPlace(it)
                                navController.navigate(ScreenState.DETAIL_SCREEN.name)
                            }
                        )
                    }
                    composable( route = ScreenState.DETAIL_SCREEN.name ) {
                        DetailItemScreen(
                            place = uiState.currentPlace
                        )
                    }
                }
            }
        }
    }
}
