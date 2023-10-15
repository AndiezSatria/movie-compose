package org.andiez.feature.tvshow.screen

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
import org.andiez.common.ui.component.content.EmptyContent
import org.andiez.common.ui.component.content.LoadingContent
import org.andiez.common.ui.component.content.WarningWithRetryButton
import org.andiez.common.ui.component.item.ShowUiItem
import org.andiez.common.ui.component.textfield.SearchTextField
import org.andiez.common.ui.theme.ActivePrimary
import org.andiez.common.ui.theme.BackgroundPrimary
import org.andiez.feature.tvshow.R
import org.andiez.presenter.model.ShowItem

/**
 * Created by AndiezSatria on 14/10/2023.
 */

@Composable
fun TvShowRoute(
    viewModel: TvViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit,
) {
    val tvList = viewModel.tvShowList.collectAsLazyPagingItems()
    val query = viewModel.query.collectAsState().value
    TvShowScreen(
        tvList = tvList,
        currentQuery = query,
        onQueryChange = viewModel::onValueChange,
        onNavigateToDetail = onNavigateToDetail,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TvShowScreen(
    modifier: Modifier = Modifier,
    tvList: LazyPagingItems<ShowItem>,
    currentQuery: String,
    onQueryChange: (String) -> Unit,
    onNavigateToDetail: (Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        SecondaryAppBar(
            title = stringResource(R.string.txt_tv_title),
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(BackgroundPrimary),
                    ) {
                        SearchTextField(
                            value = currentQuery,
                            onValueChange = {
                                tvList.refresh()
                                onQueryChange(it)
                            },
                            placeholder = "Search Tv Show",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 16.dp),
                        )
                    }
                }
                if (tvList.itemCount < 1 && currentQuery.isNotEmpty()) {
                    item {
                        EmptyContent(isNotFound = true)
                    }
                }
                items(tvList) { showItem ->
                    showItem?.let {
                        ShowUiItem(
                            onClick = { onNavigateToDetail(it.id) },
                            showItem = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                tvList.apply {
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
                                onClick = { tvList.refresh() },
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