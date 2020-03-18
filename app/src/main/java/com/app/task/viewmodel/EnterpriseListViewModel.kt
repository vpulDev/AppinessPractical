package com.app.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.task.api.responsemodel.EnterpriseListResponse
import com.app.task.base.APIResource
import com.app.task.repository.EnterpriseRepository

class EnterpriseListViewModel : ViewModel() {

    private var repository: EnterpriseRepository = EnterpriseRepository.getInstance()

    fun callEnterPriseListAPI() : LiveData<APIResource<ArrayList<EnterpriseListResponse>>>{
        return repository.callEnterPriseListAPI()
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearRepo()
    }
}
