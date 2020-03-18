package com.app.task.activity

import android.os.Bundle
import android.view.Menu
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
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_enterprise_list.*
import kotlinx.android.synthetic.main.toolbar.*


class EnterpriseListActivity : BaseActivity() {

    private var enterpriseListAdapter: EnterpriseListAdapter? = null
    private lateinit var enterpriseListViewModel: EnterpriseListViewModel
    private lateinit var enterpriseList: ArrayList<EnterpriseListResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise_list)

        setSupportActionBar(toolbar)

        enterpriseListViewModel =
            ViewModelProvider(this).get(EnterpriseListViewModel::class.java)

        callEnterpriseListAPI()

        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchEnterpriseList(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchEnterpriseList(newText)
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val item = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)

        return true
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
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
                        EnterpriseListAdapter(enterpriseList)
                    rv_enterprise_list.adapter = enterpriseListAdapter
                }
            })
        } else {
            showNoNetworkDialog()
        }
    }

    private fun showNoNetworkDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(com.app.task.R.string.app_name)
        builder.setMessage(com.app.task.R.string.no_internet_connection_available)
        builder.setPositiveButton(getString(com.app.task.R.string.retry)) { dialogInterface, which ->
            dialogInterface.dismiss()
            callEnterpriseListAPI()
        }
        builder.setNegativeButton(getString(com.app.task.R.string.exit)) { dialogInterface, which ->
            dialogInterface.dismiss()
            finish()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun searchEnterpriseList(keyword: String) {
        if (keyword.isBlank()) {
            enterpriseListAdapter?.updateData(enterpriseList)
        } else {
            val filterList = ArrayList(enterpriseList.filter {
                it.title!!.startsWith(keyword, ignoreCase = true)
            })
            enterpriseListAdapter?.updateData(filterList)
        }
        enterpriseListAdapter?.notifyDataSetChanged()
    }
}
