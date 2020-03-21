package com.ebayk.domain.usecase

import com.ebayk.domain.repository.EbayRepository
import io.reactivex.Single

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */
class GetDataUseCase(private val repository: EbayRepository) :
    UseCase<GetDataResult> {

    fun getData(): Single<GetDataResult> {
        return repository.getData().map { result ->
            if (result.error == null) {
                GetDataResult.Success(result.value!!)
            } else {
                GetDataResult.Error(result.error)
            }
        }
    }

}