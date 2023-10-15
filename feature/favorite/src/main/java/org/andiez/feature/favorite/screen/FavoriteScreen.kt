package org.andiez.feature.favorite.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import org.andiez.common.ui.component.appbar.SecondaryAppBar
import org.andiez.common.ui.component.content.EmptyFavoriteContent
import org.andiez.common.ui.component.content.LoadingContent
import org.andiez.common.ui.component.content.WarningWithRetryButton
import org.andiez.common.ui.component.item.ShowUiItem
import org.andiez.common.ui.theme.ActivePrimary
import org.andiez.common.ui.theme.BackgroundPrimary
import org.andiez.common.ui.theme.ContentFourth
import org.andiez.feature.favorite.R
import org.andiez.presenter.model.ShowItem

/**
 * Created by AndiezSatria on 15/10/2023.
 */

@Composable
fun FavoriteRoute(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onNavigateToDetail: (Int, String) -> Unit,
) {
    val showItems = viewModel.favoriteList.collectAsLazyPagingItems()
    val currentTab = viewModel.selectedTab.collectAsState().value
    FavoriteScreen(
        onNavigateToDetail = onNavigateToDetail,
        currentTab = currentTab,
        onTabChange = viewModel::onSelectedTabChanged,
        showItems = showItems,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    showItems: LazyPagingItems<ShowItem>,
    currentTab: Int,
    onTabChange: (Int) -> Unit,
    onNavigateToDetail: (Int, String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        SecondaryAppBar(
            title = stringResource(R.string.txt_favorite_title),
            actions = {}
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ActivePrimary),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(BackgroundPrimary),
            ) {
                stickyHeader {
                    TabRow(
                        selectedTabIndex = currentTab,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        containerColor = BackgroundPrimary,
                        indicator = { tabPositions ->
                            Box(
                                modifier = Modifier
                                    .tabIndicatorOffset(tabPositions[currentTab])
                                    .height(2.dp)
                                    .background(color = ActivePrimary)
                            )
                        }
                    ) {
                        Tab(
                            text = {
                                Text(
                                    "Movie",
                                    style = MaterialTheme.typography.body2
                                )
                            },
                            selected = currentTab == 0,
                            onClick = { onTabChange(0) },
                            selectedContentColor = ActivePrimary,
                            unselectedContentColor = ContentFourth,
                        )
                        Tab(
                            text = {
                                Text(
                                    "TV Show",
                                    style = MaterialTheme.typography.body2
                                )
                            },
                            selected = currentTab == 1,
                            onClick = { onTabChange(1) },
                            selectedContentColor = ActivePrimary,
                            unselectedContentColor = ContentFourth,
                        )
                    }
                }
                if (showItems.itemCount < 1) {
                    item {
                        EmptyFavoriteContent()
                    }
                }
                items(showItems) { showItem ->
                    showItem?.let {
                        ShowUiItem(
                            onClick = {
                                onNavigateToDetail(it.id, if (currentTab == 0) "movie" else "tv")
                            },
                            showItem = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                showItems.apply {
                    val error = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }

                    val loading = when {
                        loadState.prepend is LoadState.Loading -> loadState.prepend as LoadState.Loading
                        loadState.append is LoadState.Loading -> loadState.append as LoadState.Loading
                        loadState.refresh is LoadState.Loading -> loadState.refresh as LoadState.Loading
                        else -> null
                    }

                    if (loading != null) {
                        item {
                            LoadingContent(modifier = Modifier.fillMaxSize())
                        }
                    }
                    if (error != null) {
                        item {
                            WarningWithRetryButton(
                                onClick = { showItems.refresh() },
                                isEmpty = false,
                                message = error.error.message.toString(),
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }
                }
            }
        }
    }
}