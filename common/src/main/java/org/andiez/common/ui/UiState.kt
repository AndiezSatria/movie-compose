package org.andiez.common.ui

sealed class UiState<out T : Any?> {

    object Loading : UiState<Nothing>()

    object None : UiState<Nothing>()

    data class Success<out T>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}