package org.essuno.domain.product.repository

import org.essuno.domain.product.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun findAllByCategoryId(pageable: Pageable, categoryId: Int): Page<Product>

    fun findAllByCategoryIdIn(
        pageable: Pageable,
        categoryIds: List<Int>
    ): Page<Product>
}