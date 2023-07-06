package com.luu.android.ui.category

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.luu.android.model.entity.Category
import com.luu.android.simpeldesa.databinding.ActivityCategoryBinding
import com.luu.android.ui.category.adapter.CategoryAdapter
import com.luu.android.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryActivity : AppCompatActivity() {

    private var categoryAdapter: CategoryAdapter? = null
    private lateinit var viewBinding: ActivityCategoryBinding
    private val categoryViewModel: CategoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        initView()
        onSubscribe()
    }

    private fun initView() {
        categoryViewModel.fetchListCategory()
        with(viewBinding) {
            tvDone.isEnabled = false
            tvDone.setOnClickListener {
                categoryViewModel.usersRepository.setCategories(
                    categoryAdapter?.getSelectedItems() ?: emptyList()
                )
                startActivity(Intent(this@CategoryActivity, MainActivity::class.java))
            }
            categoryAdapter = CategoryAdapter {
                tvDone.isEnabled = categoryAdapter?.isAnyItemSelected() ?: false
            }
            rvCategory.adapter = categoryAdapter
        }
    }

    private fun onSubscribe() {
        categoryViewModel.fetchListCategorySuccess.observe(this) {
            val adapter = viewBinding.rvCategory.adapter as CategoryAdapter
            adapter.submitList(it)
        }

    }
}