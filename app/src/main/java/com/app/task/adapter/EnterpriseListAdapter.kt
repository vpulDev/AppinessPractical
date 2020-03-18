package com.app.task.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.task.R
import com.app.task.api.responsemodel.EnterpriseListResponse
import com.app.task.databinding.ListItemEnterpriseBinding

class EnterpriseListAdapter(
    private val activity: Activity,
    private val data: ArrayList<EnterpriseListResponse>
) : RecyclerView.Adapter<EnterpriseListAdapter.EnterpriseListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnterpriseListVH {
        val v: ListItemEnterpriseBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_enterprise,
            parent,
            false
        )
        return EnterpriseListVH(v)
    }

    override fun onBindViewHolder(holder: EnterpriseListVH, position: Int) {
        val resultsItem = data[position]
        holder.binding.model = resultsItem
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class EnterpriseListVH(val binding: ListItemEnterpriseBinding) :
        RecyclerView.ViewHolder(binding.root)
}