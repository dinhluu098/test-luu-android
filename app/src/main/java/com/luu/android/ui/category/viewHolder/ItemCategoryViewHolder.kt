package com.luu.android.ui.category.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.luu.android.model.entity.Category
import com.luu.android.simpeldesa.R
import com.luu.android.simpeldesa.databinding.ItemCategoryBinding

class ItemCategoryViewHolder(
    private val viewBinding: ItemCategoryBinding,
    private val onItemClickListener: () -> Unit
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bindingData(data: Category) {
        with(viewBinding) {
            tvTitle.text = data.name
            if (data.isSelected) {
                clRoot.setBackgroundResource(R.drawable.bg_category_selected)
            } else {
                clRoot.setBackgroundResource(R.drawable.bg_category_unselected)
            }
        }
    }
}