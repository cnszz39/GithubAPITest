package model

import com.google.gson.annotations.SerializedName

data class SearchResult(
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("incomplete_results")
        val incompleteResults: Boolean,
        val items: MutableList<Repository>
)