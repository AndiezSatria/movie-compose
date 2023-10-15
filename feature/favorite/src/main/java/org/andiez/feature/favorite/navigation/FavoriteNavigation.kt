package org.andiez.feature.favorite.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.andiez.common.ui.navigation.Screens
import org.andiez.feature.favorite.screen.FavoriteRoute

/**
 * Created by AndiezSatria on 15/10/2023.
 */

fun NavGraphBuilder.favoriteScreen(
    onNavigateToDetail: (showId: Int, showType: String) -> Unit,
) {
    composable(route = Screens.Favorite.route) {
        FavoriteRoute(onNavigateToDetail = onNavigateToDetail)
    }
}