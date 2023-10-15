package org.andiez.submissioncompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.andiez.common.ui.navigation.Screens
import org.andiez.feature.detail.navigation.detailScreen
import org.andiez.feature.favorite.navigation.favoriteScreen
import org.andiez.feature.movie.navigation.movieScreen
import org.andiez.feature.tvshow.navigation.tvShowScreen
import org.andiez.submissioncompose.ui.screen.about.AboutScreen
import org.andiez.submissioncompose.ui.screen.main.MainScreen
import org.andiez.submissioncompose.ui.screen.splash.SplashScreen

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Composable
fun BaseComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (currentRoute == Screens.Movie.route ||
                currentRoute == Screens.Tv.route ||
                currentRoute == Screens.Favorite.route ||
                currentRoute == Screens.About.route
            )
                MainScreen(
                    navController = navController
                )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screens.Splash.route) {
                SplashScreen(
                    onNavigateToMain = { navController.navigate(Screens.Movie.route) }
                )
            }
            composable(Screens.About.route) { AboutScreen() }
            movieScreen { showId ->
                navController.navigate(Screens.Detail.createRoute(showId, "movie"))
            }
            tvShowScreen { showId ->
                navController.navigate(Screens.Detail.createRoute(showId, "tv"))
            }
            favoriteScreen { showId, showType ->
                navController.navigate(Screens.Detail.createRoute(showId, showType))
            }
            detailScreen()
        }
    }
}
