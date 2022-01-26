package com.demo.retrofitwithpost.controller

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.retrofitwithpost.model.repository.RetroInstance
import com.demo.retrofitwithpost.model.repository.RetroService
import com.demo.retrofitwithpost.model.entity.Category
import com.demo.retrofitwithpost.model.entity.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewCategoryViewModel : ViewModel() {

    lateinit var createNewCategoryLiveData: MutableLiveData<CategoryResponse?>
    lateinit var loadCategoryData: MutableLiveData<CategoryResponse?>
    lateinit var deleteCategoryLiveData: MutableLiveData<CategoryResponse?>

    init {
        createNewCategoryLiveData = MutableLiveData()
        loadCategoryData = MutableLiveData()
        deleteCategoryLiveData = MutableLiveData()
    }

    fun getCreateNewCategoryObservable(): MutableLiveData<CategoryResponse?> {
        return  createNewCategoryLiveData
    }

    fun getDeleteCategoryObservable(): MutableLiveData<CategoryResponse?> {
        return  deleteCategoryLiveData
    }

    fun getLoadCategoryObservable(): MutableLiveData<CategoryResponse?> {
        return  loadCategoryData
    }

    fun createCategory(category: Category) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.createCategory(category)
        call.enqueue(object : Callback<CategoryResponse?> {
            override fun onFailure(call: Call<CategoryResponse?>, t: Throwable) {
                createNewCategoryLiveData.postValue(null)
            }

            override fun onResponse(call: Call<CategoryResponse?>, response: Response<CategoryResponse?>) {
                if(response.isSuccessful) {
                    createNewCategoryLiveData.postValue(response.body())
                } else {
                    createNewCategoryLiveData.postValue(null)
                }
            }
        })
    }

    fun updateCategory(category_id: String, category: Category) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.updateCategory(category_id, category)
        call.enqueue(object : Callback<CategoryResponse?> {
            override fun onFailure(call: Call<CategoryResponse?>, t: Throwable) {
                createNewCategoryLiveData.postValue(null)
            }

            override fun onResponse(call: Call<CategoryResponse?>, response: Response<CategoryResponse?>) {
                if(response.isSuccessful) {
                    createNewCategoryLiveData.postValue(response.body())
                } else {
                    createNewCategoryLiveData.postValue(null)
                }
            }
        })
    }

    fun deleteCategory(category_id: String?) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.deleteCategory(category_id!!)
        call.enqueue(object : Callback<CategoryResponse?> {
            override fun onFailure(call: Call<CategoryResponse?>, t: Throwable) {
                deleteCategoryLiveData.postValue(null)
            }

            override fun onResponse(call: Call<CategoryResponse?>, response: Response<CategoryResponse?>) {
                if(response.isSuccessful) {
                    deleteCategoryLiveData.postValue(response.body())
                } else {
                    deleteCategoryLiveData.postValue(null)
                }
            }
        })
    }

    fun getCategoryData(category_id: String?) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getCategory(category_id!!)
        call.enqueue(object : Callback<CategoryResponse?> {
            override fun onFailure(call: Call<CategoryResponse?>, t: Throwable) {
                loadCategoryData.postValue(null)
            }

            override fun onResponse(call: Call<CategoryResponse?>, response: Response<CategoryResponse?>) {
                if(response.isSuccessful) {
                    loadCategoryData.postValue(response.body())
                    println("val"+response.body())
                } else {
                    loadCategoryData.postValue(null)
                }
            }
        })
    }
}