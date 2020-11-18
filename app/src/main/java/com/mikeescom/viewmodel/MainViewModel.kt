package com.mikeescom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mikeescom.model.Repository
import com.mikeescom.model.dao.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: Repository? = Repository()

    fun callCharactersApi() {
        repository?.callCharacters()
    }

    fun getCharactersResponseLiveData(): LiveData<Response>? {
        return repository?.getCharacters()
    }
}