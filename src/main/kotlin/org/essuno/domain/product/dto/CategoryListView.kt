package org.essuno.domain.product.dto

data class CategoryListView(
    var parentCategory: CategoryItem = CategoryItem(),
    var categories: List<CategoryItem> = emptyList(),
)

data class CategoryItem(
    var id:Long = 0L,
    var name: String = "",
)
