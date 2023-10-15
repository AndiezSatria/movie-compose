package org.andiez.common.ui.navigation

import androidx.annotation.DrawableRes
import org.andiez.common.ui.R

/**
 * Created by AndiezSatria on 07/05/2023.
 */
sealed class BottomNavItem(val title: String, @DrawableRes val icon: Int, var screen_route: String) {
    object Movie: BottomNavItem("Movie", R.drawable.ic_movie, "movie")
    object TV: BottomNavItem("TV", R.drawable.ic_tv, "tv")
    object Favorite: BottomNavItem("Favorite", R.drawable.ic_favorite, "favorite")
    object About: BottomNavItem("About", R.drawable.ic_person, "about")
}
