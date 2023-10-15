package org.andiez.common.ui.navigation

/**
 * Created by AndiezSatria on 01/05/2023.
 */

sealed class Screens(val route: String) {
    object Splash : Screens("splash")
    object Main : Screens("main")
    object Movie : Screens("movie")
    object Tv : Screens("tv")
    object Detail : Screens("detail")
    object About : Screens("about")
    object Favorite : Screens("favorite")
}