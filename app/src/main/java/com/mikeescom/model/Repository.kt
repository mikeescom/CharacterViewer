package com.mikeescom.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mikeescom.BuildConfig
import com.mikeescom.model.dao.Response
import com.mikeescom.model.network.CharacterService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    private val TAG = Repository::class.java.simpleName
    private val service : CharacterService
    private var charactersResponseMutableLiveData = MutableLiveData<Response>()

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        service = Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterService::class.java)
    }

    fun callCharacters() {
        service.getCharacters(BuildConfig.URL).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                charactersResponseMutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e(TAG, "Error: ${t.message}")
            }

        })
    }

    fun getCharacters() : LiveData<Response>{
        return  charactersResponseMutableLiveData
    }
}