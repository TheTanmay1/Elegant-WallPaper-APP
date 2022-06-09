package com.unwiringapps.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unwiringapps.wallpaperapp.CategoryenlargeActivity
import com.unwiringapps.wallpaperapp.FinalWallpaper
import com.unwiringapps.wallpaperapp.Model.thecategoriesModel
import com.unwiringapps.wallpaperapp.Model.thecolortoneModel
import com.unwiringapps.wallpaperapp.R

class categoriesAdapter(
    val requireContext: Context,
    val listcategories: ArrayList<thecategoriesModel>
) : RecyclerView.Adapter<categoriesAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageviewbro = itemView.findViewById<ImageView>(R.id.categ_image)
        val textviewbro = itemView.findViewById<TextView>(R.id.cat_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_cat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {

        holder.textviewbro.text = listcategories[position].name
        Glide.with(requireContext).load(listcategories[position].link).into(holder.imageviewbro)

        holder.itemView.setOnClickListener {
            // itemView is the entire layout that we have created for repeated item {item_bom.xml}
            val intent = Intent(requireContext, CategoryenlargeActivity::class.java)



            intent.putExtra("vid", listcategories[position].id)
            intent.putExtra("name", listcategories[position].name)

            requireContext.startActivity(intent)
        }

//        val colorsbro = listthecolortone[position].color
//        val colordude: Int = Color.parseColor(colorsbro!!)  // to parse string into int
//        holder.cardViewbro.setCardBackgroundColor(colordude)

    }

    override fun getItemCount() = listcategories.size

}

