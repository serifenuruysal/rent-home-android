package com.ebayk.data.repository

import com.ebayk.data.service.ApiService
import com.ebayk.domain.entity.Response
import com.ebayk.domain.repository.EbayRepository
import com.ebayk.domain.repository.Result
import io.reactivex.Single

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */
class EbayRepositoryImpl :
    EbayRepository {
    private val restApi = ApiService.create()
    override fun getData(): Single<Result<Response, Exception>> {
        return restApi.getData()
            .map { response ->
                Result<Response, Exception>(response, null)


            }
            .onErrorReturn {
                Result<Response, Exception>(null, Exception())
            }
    }

}