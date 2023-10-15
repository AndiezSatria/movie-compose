package org.andiez.feature.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.andiez.common.ui.navigation.Screens
import org.andiez.common.util.CommonConstant
import org.andiez.feature.detail.screen.DetailRoute

/**
 * Created by AndiezSatria on 15/10/2023.
 */

fun NavGraphBuilder.detailScreen() {
    composable(
        route = Screens.Detail.route,
        arguments = listOf(
            navArgument(CommonConstant.ID_ARGS) {
                type = NavType.IntType
            },
            navArgument(CommonConstant.TYPE_ARGS) {
                type = NavType.StringType
            },
        )
    ) {
        DetailRoute()
    }
}