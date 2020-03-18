package com.app.task.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.FragmentActivity
import com.app.task.R

abstract class BaseActivity : FragmentActivity() {

    private var progressDialog: Dialog? = null

    fun showProgressDialog(context: Context) {
        if (progressDialog == null || !progressDialog?.isShowing!!) {
            progressDialog = Dialog(context)
            progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressDialog?.setContentView(R.layout.custom_progressbar)
            progressDialog?.setCancelable(false)
            progressDialog?.show()
        }
    }

    fun hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog?.isShowing!!) {
                progressDialog?.dismiss()
            }
        } catch (throwable: Throwable) {

        } finally {
            progressDialog?.dismiss()
        }
    }
}