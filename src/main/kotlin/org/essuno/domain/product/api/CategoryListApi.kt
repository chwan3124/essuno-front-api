package org.essuno.domain.product.api

import org.essuno.domain.product.dto.CategoryListView
import org.essuno.domain.product.entity.Category
import org.essuno.domain.product.service.CategoryListService
import org.essuno.global.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class CategoryListApi(
    private val categoryListService: CategoryListService
) {

    @GetMapping("/categories/{categoryId}")
    fun getCategory(@PathVariable("categoryId") categoryId: Int): ApiResponse<CategoryListView>{
        return ApiResponse.success(categoryListService.getCategories(categoryId))
    }
}