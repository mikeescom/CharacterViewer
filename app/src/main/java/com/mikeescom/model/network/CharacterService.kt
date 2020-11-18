package com.mikeescom.model.network

import com.mikeescom.model.dao.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface CharacterService {
    @GET
    fun getCharacters(@Url url: String?): Call<Response>
}