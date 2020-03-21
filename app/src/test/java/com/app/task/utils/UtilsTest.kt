package com.app.task.utils

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.util.Log
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito


class UtilsTest {
    @Mock
    var context: Context? = null
    @Mock
    var resources: Resources? = null
    @Mock
    var connectivityManager: ConnectivityManager? = null
    @Mock
    var networkInfo: Utils? = null

    @Rule
    var thrown = ExpectedException.none()

    @Before
    fun setup() {
        PowerMockito.mockStatic(Log::class.java)
        Mockito.`when`(context?.getApplicationContext()).thenReturn(context)
        Mockito.`when`(context?.getResources()).thenReturn(resources)
        Mockito.`when`(context?.getSystemService(Context.CONNECTIVITY_SERVICE))
            .thenReturn(connectivityManager)
        Mockito.`when`(connectivityManager!!.activeNetworkInfo.isConnected)
            .thenReturn(Utils.isNetworkAvailable())
    }
}