package com.ebayk.domain.repository

import com.ebayk.domain.entity.Response
import io.reactivex.Single

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */

interface EbayRepository {
    fun getData(): Single<Result<Response, Exception>>

}