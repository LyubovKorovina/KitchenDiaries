package com.example.kitchendiaries.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kitchendiaries.R
import com.example.kitchendiaries.entities.MealCategoryItems
import com.example.kitchendiaries.entities.RecipeModel
import android.widget.ImageView

class MainCatAdapter: RecyclerView.Adapter<MainCatAdapter.RecipeViewholder>() {

    var listener: OnItemClickListener? = null
    var ctx: Context? = null
    var mainCatList = ArrayList<MealCategoryItems>()

    class RecipeViewholder(view: View): RecyclerView.ViewHolder(view) {
        val tvRecipeName: TextView = view.findViewById(R.id.tv_dish_name)
        val imgDish: ImageView = view.findViewById(R.id.img_dish) // Add this line for "img_dish" ImageView
    }

    //this function is needed to set data from home HomeActivity
    fun setData(arrData : List<MealCategoryItems>){
        mainCatList = arrData as ArrayList<MealCategoryItems>
    }

    fun setClickListener(listener1: OnItemClickListener) {
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewholder {
        ctx = parent.context
        return RecipeViewholder(LayoutInflater.from(parent.context).inflate(R.layout.main_category_item_rv, parent, false))
    }

    override fun getItemCount(): Int {
        return mainCatList.size
    }

    override fun onBindViewHolder(holder: RecipeViewholder, position: Int) {
        holder.tvRecipeName.text = mainCatList[position].strcategory
        Glide.with(ctx!!).load(mainCatList[position].strcategorythumb).into(holder.imgDish)
        holder.itemView.setOnClickListener {
            listener?.onClicked(mainCatList[position].strcategory)
        }
    }

    interface OnItemClickListener {
        fun onClicked(categoryName:String)
    }
}