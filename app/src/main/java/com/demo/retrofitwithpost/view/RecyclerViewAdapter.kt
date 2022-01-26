package com.demo.retrofitwithpost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.retrofitwithpost.R
import com.demo.retrofitwithpost.model.entity.Category
import kotlinx.android.synthetic.main.recucler_row_list.view.*

class RecyclerViewAdapter(val clickListener: OnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var categoryList = mutableListOf<Category>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recucler_row_list, parent, false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
       holder.bind(categoryList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemEditCLick(categoryList[position])
        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewName = view.textViewName
        val textViewRestaurant = view.textViewRestaurant
        /*val textViewStats = view.textViewStats*/

        fun bind(data : Category) {
            textViewName.text = data.name
            textViewRestaurant.text = data.restaurant
           /* textViewStats.text = data.status*/
        }
    }

    interface OnItemClickListener {
        fun onItemEditCLick(category : Category)
    }
}