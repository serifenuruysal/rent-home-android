package com.ebayk.domain.usecase

import com.ebayk.domain.entity.Response

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */

sealed class GetDataResult : UseCaseResult {

    data class Success(val response: Response) : GetDataResult()
    data class Error(val exception: Exception) : GetDataResult()
}