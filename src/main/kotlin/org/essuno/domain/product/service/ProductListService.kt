package org.essuno.domain.product.service

import org.essuno.domain.product.dto.ProductItem
import org.essuno.domain.product.dto.ProductListView
import org.essuno.domain.product.repository.CategoryRepository
import org.essuno.domain.product.repository.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductListService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {
    fun getProducts(page: Int, categoryId: Int): ProductListView  {
        val pageable = PageRequest.of(page, 10)
        val category = categoryRepository.findById(categoryId).orElseThrow { RuntimeException("Category not found!") };

        val productPage = if(category.parentId == null){
            val childCategories = categoryRepository.findAllByParentId(categoryId)
            val categoryIds = listOf(categoryId) + childCategories.mapNotNull  { it.id }
            productRepository.findAllByCategoryIdIn(pageable, categoryIds)
        }else{
            productRepository.findAllByCategoryId(pageable, category.id!!)
        }

        val products = productPage.content.map { product ->
            ProductItem(
                id = product.id,
                name = product.name,
                category = product.category.name,
                price = product.price,
                stock = product.stock,
                status =product.status,
                createdAt = product.createdAt,
            )
        }

        return ProductListView(
            totalCount = productPage.totalElements.toInt(),
            totalPage = productPage.totalPages,
            products = products,
        )
    }
}