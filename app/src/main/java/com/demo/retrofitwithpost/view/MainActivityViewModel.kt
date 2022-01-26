package com.demo.retrofitwithpost.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.retrofitwithpost.model.repository.RetroInstance
import com.demo.retrofitwithpost.model.repository.RetroService
import com.demo.retrofitwithpost.model.entity.CategoryList
import com.demo.retrofitwithpost.model.entity.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {


    lateinit var recyclerListData: MutableLiveData<CategoryList>

    init {
        recyclerListData = MutableLiveData()

    }


    fun getCategoryListObserverable() : MutableLiveData<CategoryList> {
        return recyclerListData
    }

    fun getCategoryList() {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getCategoryList()
        call.enqueue(object : Callback<CategoryList>{
            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if(response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }
        })
    }

   fun searchCategory(searchText: String) {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.searchCategory(searchText)
        call.enqueue(object : Callback<CategoryList>{
            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if(response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }
        })
    }
}