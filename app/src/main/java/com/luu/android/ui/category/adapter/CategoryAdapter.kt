package com.luu.android.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.luu.android.ext.nullToZero
import com.luu.android.model.entity.Category
import com.luu.android.model.entity.CategoryItemCallback
import com.luu.android.simpeldesa.databinding.ItemCategoryBinding
import com.luu.android.ui.category.viewHolder.ItemCategoryViewHolder

class CategoryAdapter(
    private val onItemClickListener: () -> Unit,
) : ListAdapter<Category, ItemCategoryViewHolder>(CategoryItemCallback()) {

    private val selectedItems = mutableListOf<Int>()
    private var selectedItemCount = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = ItemCategoryBinding.inflate(inflater, parent, false)
        return ItemCategoryViewHolder(viewBinding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ItemCategoryViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindingData(data)
        holder.itemView.setOnClickListener {
            toggleSelection(data)
            onItemClickListener.invoke()
        }
    }

    fun getSelectedItems(): List<Category> {
        return currentList.filter { it.isSelected }
    }

    fun isAnyItemSelected(): Boolean {
        return selectedItemCount > 0
    }

    private fun toggleSelection(item: Category) {
        item.isSelected = !item.isSelected
        if (item.isSelected) {
            selectedItems.add(item.id.nullToZero())
            selectedItemCount++
        } else {
            selectedItems.remove(item.id)
            selectedItemCount--
        }
        notifyItemChanged(currentList.indexOf(item))
    }
}
