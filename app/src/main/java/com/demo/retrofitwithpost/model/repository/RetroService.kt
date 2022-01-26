package com.demo.retrofitwithpost.model.repository

import com.demo.retrofitwithpost.model.entity.Category
import com.demo.retrofitwithpost.model.entity.CategoryList
import com.demo.retrofitwithpost.model.entity.CategoryResponse
import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    @GET("/api/v1/category/list/6")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getCategoryList(): Call<CategoryList>

    @GET("/api/v1/category/4")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun searchCategory(@Query("name") searchText: String): Call<CategoryList>


    @GET("/api/v1/category/2")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getCategory(@Path("category_id") category_id: String): Call<CategoryResponse>

    @POST("/api/v1/category/save")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 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")
    fun createCategory(@Body params: Category): Call<CategoryResponse>

    @PATCH("/api/v1/category/save")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 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")
    fun updateCategory(@Path("category_id") category_id: String, @Body params: Category): Call<CategoryResponse>

    @DELETE("/api/v1/category/9")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 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")
    fun deleteCategory(@Path("category_id") category_id: String): Call<CategoryResponse>

}