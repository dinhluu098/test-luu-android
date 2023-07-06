package com.luu.android.model.entity

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    var isSelected: Boolean = false
)

class CategoryItemCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem == newItem
}