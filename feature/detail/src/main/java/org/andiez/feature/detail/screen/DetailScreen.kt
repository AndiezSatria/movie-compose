package org.andiez.feature.detail.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import org.andiez.common.ui.UiState
import org.andiez.common.ui.component.appbar.SecondaryAppBar
import org.andiez.common.ui.component.content.LoadingContent
import org.andiez.common.ui.component.content.WarningWithRetryButton
import org.andiez.common.ui.component.item.CastUiItem
import org.andiez.common.ui.theme.ActivePrimary
import org.andiez.common.ui.theme.BackgroundPrimary
import org.andiez.common.ui.theme.ContentFourth
import org.andiez.common.ui.theme.ContentPrimary
import org.andiez.common.ui.theme.ContentTertiary
import org.andiez.common.ui.theme.ErrorPrimary
import org.andiez.common.util.CommonConstant
import org.andiez.feature.detail.R
import org.andiez.presenter.model.CastItem
import org.andiez.presenter.model.DetailItem
import java.lang.Float.min

/**
 * Created by AndiezSatria on 15/10/2023.
 */


@Composable
fun DetailRoute(
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val detailUiState = viewModel.uiState.collectAsState().value
    val id = viewModel.id.collectAsState().value
    val type = viewModel.type.collectAsState().value

    DetailScreen(
        detailUiState = detailUiState,
        scrollState = rememberScrollState(),
        onRetryCast = {
            if (type == "movie") viewModel.getMovieDetail(id)
            else viewModel.getTvDetail(id)
        },
        onRetryDetail = { viewModel.getCastDetail(type, id) },
        onSetFavorite = { viewModel.setFavorite(type, it) }
    )
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    scrollState: ScrollState,
    onRetryCast: () -> Unit,
    onRetryDetail: () -> Unit,
    onSetFavorite: (item: DetailItem) -> Unit,
) {
    val detailItem = detailUiState.showDetail
    Box {

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            when (detailItem) {
                is UiState.Success -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .background(BackgroundPrimary)
                            .graphicsLayer {
                                alpha =
                                    1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
                                translationY = 0.5f * scrollState.value
                            },
                        contentAlignment = Alignment.BottomCenter,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(200.dp),
                                model = CommonConstant.IMG_W500 + detailItem.data.img,
                                contentDescription = null,
                            )
                            IconButton(
                                modifier = Modifier.background(ActivePrimary),
                                onClick = {
                                    onSetFavorite(detailItem.data)
                                },
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = if (detailItem.data.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border),
                                    contentDescription = "Favorite Button",
                                    tint = if (detailItem.data.isFavorite) ErrorPrimary else BackgroundPrimary
                                )
                            }
                        }
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp),
                            model = CommonConstant.IMG_ORI + detailItem.data.backdrop,
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = detailItem.data.title,
                        style = MaterialTheme.typography.h5.copy(
                            color = ContentPrimary,
                            fontWeight = FontWeight.Normal,
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "\"${detailItem.data.title}\"",
                        style = MaterialTheme.typography.body1.copy(
                            color = ContentTertiary,
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.h6.copy(
                            color = ContentPrimary,
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = detailItem.data.overview,
                        style = MaterialTheme.typography.body1.copy(
                            color = ContentFourth,
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Credits",
                        style = MaterialTheme.typography.h6.copy(
                            color = ContentPrimary,
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CastContent(castUiState = detailUiState.casts) {
                        onRetryCast()
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                is UiState.Loading -> {
                    LoadingContent(modifier = Modifier.height(500.dp))
                }

                is UiState.Error -> {
                    WarningWithRetryButton(
                        modifier = Modifier.height(500.dp),
                        isEmpty = false,
                        onClick = { onRetryDetail() },
                        message = detailItem.errorMessage,
                    )
                }

                else -> {

                }
            }
        }
        SecondaryAppBar(
            modifier = Modifier.alpha(
                min(
                    1f,
                    (scrollState.value.toFloat() / scrollState.maxValue) * 5f
                )
            ),
            title = "Detail Movie",
            actions = {
                if (detailItem is UiState.Success)
                    IconButton(
                        onClick = {
                            onSetFavorite(detailItem.data)
                        },
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = if (detailItem.data.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border),
                            contentDescription = "Favorite Button",
                            tint = BackgroundPrimary
                        )
                    }
            },
        )
    }
}

@Composable
fun CastContent(
    modifier: Modifier = Modifier,
    castUiState: UiState<List<CastItem>>,
    onRetryCast: () -> Unit,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        when (castUiState) {
            is UiState.Success -> {
                items(castUiState.data, key = { it.id }) { item ->
                    CastUiItem(castItem = item)
                }
            }

            is UiState.Loading -> {
                item {
                    LoadingContent(modifier = Modifier.height(160.dp))
                }
            }

            is UiState.Error -> {
                item {
                    WarningWithRetryButton(
                        onClick = onRetryCast,
                        isEmpty = false,
                        message = castUiState.errorMessage,
                        modifier = Modifier.height(160.dp),
                    )
                }
            }

            else -> {}
        }
    }
}