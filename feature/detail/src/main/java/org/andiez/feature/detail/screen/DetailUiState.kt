package org.andiez.feature.detail.screen

import org.andiez.common.ui.UiState
import org.andiez.presenter.model.CastItem
import org.andiez.presenter.model.DetailItem

/**
 * Created by AndiezSatria on 15/10/2023.
 */
data class DetailUiState(
    val showDetail: UiState<DetailItem>,
    val casts: UiState<List<CastItem>>,
)