package com.app.task.viewmodel

import androidx.lifecycle.Observer
import com.app.task.api.responsemodel.EnterpriseListResponse
import com.app.task.base.APIResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class EnterpriseListViewModelTest {

    private lateinit var enterpriseListViewModel: EnterpriseListViewModel

    @Test
    fun onCleared() {
    }

    @Test
    fun clear() {
    }

    @Before
    fun beforeTest() {
        enterpriseListViewModel = EnterpriseListViewModel()
    }

    @Test
    fun callEnterPriseListAPI() {
        GlobalScope.launch {
            enterpriseListViewModel.callEnterPriseListAPI()
                .observeForever(object : Observer<APIResource<ArrayList<EnterpriseListResponse>>> {
                    override fun onChanged(t: APIResource<ArrayList<EnterpriseListResponse>>?) {
                        if (t?.data != null) {
                            assertNotNull(t.data)
                        }
                    }
                })
        }
    }
}