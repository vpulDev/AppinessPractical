package com.app.task.api.responsemodel

import com.google.gson.annotations.SerializedName

data class EnterpriseListResponse(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("end.time")
	val endTime: String? = null,

	@field:SerializedName("s.no")
	val sNo: Int? = null,

	@field:SerializedName("num.backers")
	val numBackers: String? = null,

	@field:SerializedName("blurb")
	val blurb: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("amt.pledged")
	val amtPledged: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("by")
	val by: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("percentage.funded")
	val percentageFunded: Int? = null
)