package com.ebayk.presentation.viewmodel

import com.ebayk.domain.entity.Response

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */

sealed class DataViewModelState {
    abstract val loadedAllItems: Boolean
    abstract val response: Response?
}

data class LoadingState(
    override val loadedAllItems: Boolean,
    override val response: Response?
) : DataViewModelState()

data class LoadedState(
    override val loadedAllItems: Boolean,
    override val response: Response
) : DataViewModelState()

data class ErrorState(
    val errorMessage: String,
    override val loadedAllItems: Boolean,
    override val response: Response?
) : DataViewModelState()