package org.andiez.feature.tvshow.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.andiez.common.ui.navigation.Screens
import org.andiez.feature.tvshow.screen.TvShowRoute

/**
 * Created by AndiezSatria on 14/10/2023.
 */

fun NavGraphBuilder.tvShowScreen(
    onNavigateToDetail: (showId: Int) -> Unit,
) {
    composable(route = Screens.Tv.route) {
        TvShowRoute(onNavigateToDetail = onNavigateToDetail)
    }
}