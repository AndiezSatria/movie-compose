package org.andiez.feature.movie.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.andiez.common.ui.navigation.Screens
import org.andiez.feature.movie.screen.MovieRoute

/**
 * Created by AndiezSatria on 14/10/2023.
 */

fun NavGraphBuilder.movieScreen(
    onNavigateToDetail: (showId: Int) -> Unit,
) {
    composable(route = Screens.Movie.route) {
        MovieRoute(onNavigateToDetail = onNavigateToDetail)
    }
}