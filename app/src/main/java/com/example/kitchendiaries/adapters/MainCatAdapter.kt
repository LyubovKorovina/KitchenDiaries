package com.example.kitchendiaries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchendiaries.R
import com.example.kitchendiaries.entities.RecipeModel
import com.example.kitchendiaries.database.
class MainCatAdapter: RecyclerView.Adapter<MainCatAdapter.RecipeViewholder>() {

    var mainCatList = ArrayList<RecipeModel>()

    class RecipeViewholder(view: View): RecyclerView.ViewHolder(view){

    }

    //this function is needed to set data from home HomeActivity
    fun setData(arrData : List<RecipeModel>){
        mainCatList = arrData as ArrayList<RecipeModel>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewholder {
        return RecipeViewholder(LayoutInflater.from(parent.context).inflate(R.layout.main_category_item_rv, parent, false))
    }

    override fun getItemCount(): Int {
        return mainCatList.size
    }

    override fun onBindViewHolder(holder: RecipeViewholder, position: Int) {
        holder.itemView.tv_dish_name.text =
    }

}