package com.demo.retrofitwithpost.model.entity

data class CategoryList(val data: List<Category>)

data class Category(
    val id:String?,
    val name:String?,
    val restaurant:String?
)

data class CategoryResponse(val code: Int?, val meta: String?, val data: Category?)