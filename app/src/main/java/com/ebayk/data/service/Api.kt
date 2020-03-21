package com.ebayk.data.service

import com.ebayk.domain.entity.Response
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */

interface Api {

    @GET("candidates/ads/1118635128")
    fun getData(): Single<Response>
}