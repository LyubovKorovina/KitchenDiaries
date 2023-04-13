package com.example.kitchendiaries.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kitchendiaries.R
import com.example.kitchendiaries.entities.MealsItems
import com.example.kitchendiaries.entities.RecipeModel

class SubCatAdapter: RecyclerView.Adapter<SubCatAdapter.RecipeViewholder>() {

    var ctx :Context? = null
    var subCatList = ArrayList<MealsItems>()


    class RecipeViewholder(view: View): RecyclerView.ViewHolder(view){
        val tvRecipeName: TextView = itemView.findViewById(R.id.tv_dish_name)
        val img_dish: ImageView = itemView.findViewById(R.id.img_dish) // Define the img_dish ImageView
    }

    //this function is needed to set data from home HomeActivity
    fun setData(arrData : List<MealsItems>){
        subCatList = arrData as ArrayList<MealsItems>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewholder {
        ctx=parent.context
        return RecipeViewholder(LayoutInflater.from(parent.context).inflate(R.layout.sub_category_item_rv, parent, false))
    }

    override fun getItemCount(): Int {
        return subCatList.size
    }

    override fun onBindViewHolder(holder: RecipeViewholder, position: Int) {
        val recipes = subCatList[position]
        holder.apply {
            tvRecipeName.text = recipes.strMeal
            Glide.with(ctx!!).load(subCatList[position].strMealThumb).into(holder.img_dish)
        }
    }
    }

