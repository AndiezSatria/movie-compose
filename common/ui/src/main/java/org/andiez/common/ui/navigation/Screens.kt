package org.andiez.common.ui.navigation

import org.andiez.common.util.CommonConstant

/**
 * Created by AndiezSatria on 01/05/2023.
 */

sealed class Screens(val route: String) {
    object Splash : Screens("splash")
    object Main : Screens("main")
    object Movie : Screens("movie")
    object Tv : Screens("tv")
    object Detail : Screens("detail/{${CommonConstant.ID_ARGS}}/{${CommonConstant.TYPE_ARGS}}") {
        fun createRoute(id: Int, type: String) = "detail/$id/$type"
    }

    object About : Screens("about")
    object Favorite : Screens("favorite")
}