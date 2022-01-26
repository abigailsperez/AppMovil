package com.demo.retrofitwithpost.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.retrofitwithpost.R
import com.demo.retrofitwithpost.model.entity.Category
import com.demo.retrofitwithpost.model.entity.CategoryResponse
import kotlinx.android.synthetic.main.activity_create_new_category.*

class CreateNewCategoryActivity : AppCompatActivity() {

    lateinit var viewModel: CreateNewCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_category)

        val category_id = intent.getStringExtra("category_id")
        initViewModel()
        createCategoryObservable()

       if(category_id != null) {
            loadCategoryData(category_id)
        }
        createButton.setOnClickListener {
            createCategory(category_id)
        }
        deleteButton.setOnClickListener {
            deleteCategory(category_id)
        }
    }

   private fun deleteCategory(category_id: String?) {
        viewModel.getDeleteCategoryObservable().observe(this, Observer <CategoryResponse?>{
            if(it == null) {
                Toast.makeText(this@CreateNewCategoryActivity, "Failed to delete user...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CreateNewCategoryActivity, "Successfully deleted user...", Toast.LENGTH_LONG).show()
                finish()
            }
        })
        viewModel.deleteCategory(category_id)
    }
    private fun loadCategoryData(category_id: String?) {
        viewModel.getLoadCategoryObservable().observe(this, Observer <CategoryResponse?>{
            if(it != null) {
                editTextName.setText(it.data?.name)
                editTextRestaurant.setText(it.data?.restaurant)
                createButton.setText("Update")
                deleteButton.visibility =  View.VISIBLE
            }
        })
        viewModel.getCategoryData(category_id)
    }
    private fun createCategory(category_id: String?){
        val category = Category("", editTextName.text.toString(), editTextRestaurant.text.toString())

        if(category_id == null)
            viewModel.createCategory(category)
        else
            viewModel.updateCategory(category_id, category)

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CreateNewCategoryViewModel::class.java)

    }

    private fun createCategoryObservable() {
        viewModel.getCreateNewCategoryObservable().observe(this, Observer <CategoryResponse?>{
            if(it == null) {
                Toast.makeText(this@CreateNewCategoryActivity, "Failed to create/update new user...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CreateNewCategoryActivity, "Successfully created/updated user...", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }
}