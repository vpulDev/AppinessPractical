package com.app.task.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.task.utils.Utils
import com.app.task.R
import com.app.task.api.ApiHelperClass
import com.app.task.api.responsemodel.EnterpriseListResponse
import com.app.task.base.APIResource
import com.app.task.base.BaseApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class EnterpriseRepository private constructor() {

    private var disposable: Disposable? = null

    fun callEnterPriseListAPI(): LiveData<APIResource<ArrayList<EnterpriseListResponse>>> {

        val data = MutableLiveData<APIResource<ArrayList<EnterpriseListResponse>>>()
        data.value = APIResource.loading(null)

        if (Utils.isNetworkAvailable()) {
            disposable = ApiHelperClass.getAPIClient().callEnterPriseListAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ responseModel ->
                    if (responseModel == null) {
                        data.value = APIResource.error("", null)
                        return@subscribe
                    }

                    data.value = APIResource.success(responseModel)
                }, { e ->
                    Timber.e(e)
                    data.postValue(APIResource.error(e.localizedMessage ?: "", null))
                })
        } else {
            data.value = APIResource.error(
                BaseApplication.getApplicationContext()
                    .getString(R.string.no_internet_connection_available)
                , null
            )
        }
        return data
    }

    fun clearRepo() {
        disposable?.dispose()
    }

    companion object {

        @Volatile
        private var instance: EnterpriseRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: EnterpriseRepository().also { instance = it }
            }
    }
}
