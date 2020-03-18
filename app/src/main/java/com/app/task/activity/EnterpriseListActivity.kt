package com.app.task.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.task.R
import com.app.task.adapter.EnterpriseListAdapter
import com.app.task.api.responsemodel.EnterpriseListResponse
import com.app.task.base.BaseActivity
import com.app.task.base.Status
import com.app.task.utils.Utils
import com.app.task.viewmodel.EnterpriseListViewModel
import kotlinx.android.synthetic.main.activity_enterprise_list.*


class EnterpriseListActivity : BaseActivity() {

    private lateinit var enterpriseListAdapter: EnterpriseListAdapter
    private lateinit var enterpriseListViewModel: EnterpriseListViewModel
    private lateinit var enterpriseList: ArrayList<EnterpriseListResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise_list)

        enterpriseListViewModel =
            ViewModelProvider(this).get(EnterpriseListViewModel::class.java)

        callEnterpriseListAPI()
    }

    private fun callEnterpriseListAPI() {
        if (Utils.isNetworkAvailable()) {
            enterpriseListViewModel.callEnterPriseListAPI().observe(this, Observer { response ->
                if (response.status == Status.LOADING) {
                    showProgressDialog(this)
                } else if (response.status == Status.ERROR) {
                    hideProgressDialog()
                    response.message?.let {
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    }
                } else {
                    hideProgressDialog()
                    val data = response.data!!
                    //Getting list of data according to sort by title
                    enterpriseList = ArrayList(data.sortedBy { model ->
                        model.title
                    })
                    enterpriseListAdapter =
                        EnterpriseListAdapter(this, enterpriseList)
                    rv_enterprise_list.adapter = enterpriseListAdapter
                }
            })
        } else {
            showNoNetworkDialog()
        }
    }

    private fun showNoNetworkDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage(R.string.no_internet_connection_available)
        builder.setPositiveButton(getString(R.string.retry)) { dialogInterface, which ->
            dialogInterface.dismiss()
            callEnterpriseListAPI()
        }
        builder.setNegativeButton(getString(R.string.exit)) { dialogInterface, which ->
            dialogInterface.dismiss()
            finish()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}
