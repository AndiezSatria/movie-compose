package org.andiez.feature.detail.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Created by AndiezSatria on 15/10/2023.
 */


@Composable
fun DetailRoute(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val detailUiState = viewModel.uiState.collectAsState().value
    val id = viewModel.id.collectAsState().value
    val type = viewModel.type.collectAsState().value


}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
) {

}

@Composable
fun CastContent(
    modifier: Modifier = Modifier,
) {

}