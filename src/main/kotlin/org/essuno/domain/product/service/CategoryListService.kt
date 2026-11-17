package org.essuno.domain.product.service

import org.essuno.domain.product.dto.CategoryItem
import org.essuno.domain.product.dto.CategoryListView
import org.essuno.domain.product.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryListService(
    private val categoryRepository: CategoryRepository
) {

    fun getCategories(categoryId: Long): CategoryListView{
        val categoryListView : CategoryListView = CategoryListView()
        val category = categoryRepository.findById(categoryId).orElseThrow { RuntimeException("Category not found!") };

        if(category.parentId == null){
            categoryListView.parentCategory.id = category.id!!;
            categoryListView.parentCategory.name = category.name;

            val childrenCategories = categoryRepository.findAllByParentId(category.id)
            val categories = childrenCategories.map { it -> CategoryItem(id = it.id!!, name = it.name) }
            categoryListView.categories = categories
        } else {
            val parentCategory = categoryRepository.findById(category.parentId!!).orElseThrow { RuntimeException("Category not found!") };
            categoryListView.parentCategory.id = category.id!!;
            categoryListView.parentCategory.name = category.name;

            val childrenCategories = categoryRepository.findAllByParentId(category.id)
            val categories = childrenCategories.map { it -> CategoryItem(id = it.id!!, name = it.name) }
            categoryListView.categories = categories
        }

        return categoryListView;
    }
}