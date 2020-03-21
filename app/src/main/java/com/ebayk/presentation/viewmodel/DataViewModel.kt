package com.ebayk.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ebayk.data.repository.EbayRepositoryImpl
import com.ebayk.domain.usecase.GetDataResult
import com.ebayk.domain.usecase.GetDataUseCase
import com.ebayk.domain.usecase.UseCaseResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */

class DataViewModel : ViewModel() {

    val stateLiveData = MutableLiveData<DataViewModelState>()
    private var repository = EbayRepositoryImpl()

    init {
        stateLiveData.value =
            LoadingState(false, null)
    }

    @SuppressLint("CheckResult")
    fun getData() {
        val useCase = GetDataUseCase(repository)
        useCase.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onDataResponseReceived, this::onError)
    }


    private fun onDataResponseReceived(result: UseCaseResult) {
        when (result) {
            is GetDataResult.Success -> {
                stateLiveData.value =
                    LoadedState(
                        true,
                        result.response
                    )

            }
            is GetDataResult.Error -> {
                stateLiveData.value =
                    ErrorState(
                        result.exception.localizedMessage!!,
                        false,
                        null
                    )
            }
        }
    }

    private fun onError(error: Throwable) {
        stateLiveData.value =
            ErrorState(
                error.message ?: "",
                false,
                null
            )
    }


}