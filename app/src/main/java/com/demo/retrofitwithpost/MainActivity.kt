package com.demo.retrofitwithpost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.retrofitwithpost.controller.CreateNewCategoryActivity
import com.demo.retrofitwithpost.model.entity.Category
import com.demo.retrofitwithpost.model.entity.CategoryList
import com.demo.retrofitwithpost.view.MainActivityViewModel
import com.demo.retrofitwithpost.view.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_create_new_category.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
        searchCategory()

        createCategoryButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateNewCategoryActivity::class.java))
        }
    }

   private fun searchCategory() {
        search_button.setOnClickListener {
            if(!TextUtils.isEmpty(searchEditText.text.toString())) {
                viewModel.searchCategory(searchEditText.text.toString())
            } else {
                viewModel.getCategoryList()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter

        }
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getCategoryListObserverable().observe(this, Observer<CategoryList> {
            if(it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                recyclerViewAdapter.categoryList = it.data.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getCategoryList()
    }

    override fun onItemEditCLick(category: Category) {
        val intent = Intent(this@MainActivity, CreateNewCategoryActivity::class.java)
        intent.putExtra("category_id", category.id)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 1000) {
            viewModel.getCategoryList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}