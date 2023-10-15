package org.andiez.submissioncompose.ui.screen.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.andiez.common.ui.theme.ActivePrimary
import org.andiez.common.ui.theme.BackgroundPrimary
import org.andiez.common.ui.theme.InactiveSecondary
import org.andiez.common.ui.navigation.BottomNavItem

/**
 * Created by AndiezSatria on 07/05/2023.
 */

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val items = listOf(
        BottomNavItem.Movie,
        BottomNavItem.TV,
        BottomNavItem.Favorite,
        BottomNavItem.About,
    )

    BottomNavigation(
        modifier = modifier,
        backgroundColor = BackgroundPrimary,
        contentColor = ActivePrimary,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = ActivePrimary,
                unselectedContentColor = InactiveSecondary,
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}