package com.example.kitchendiaries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchendiaries.R
import com.example.kitchendiaries.entities.RecipeModel

class SubCatAdapter: RecyclerView.Adapter<SubCatAdapter.RecipeViewholder>() {

    var subCatList = ArrayList<RecipeModel>()


    class RecipeViewholder(view: View): RecyclerView.ViewHolder(view){
        val tvRecipeName: TextView = itemView.findViewById(R.id.tv_dish_name)
    }

    //this function is needed to set data from home HomeActivity
    fun setData(arrData : List<RecipeModel>){
        subCatList = arrData as ArrayList<RecipeModel>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewholder {

        return RecipeViewholder(LayoutInflater.from(parent.context).inflate(R.layout.sub_category_item_rv, parent, false))
    }

    override fun getItemCount(): Int {
        return subCatList.size
    }

    override fun onBindViewHolder(holder: RecipeViewholder, position: Int) {
        val recipes = subCatList[position]
        holder.apply {
            tvRecipeName.text = recipes.mealName
        }
    }

}