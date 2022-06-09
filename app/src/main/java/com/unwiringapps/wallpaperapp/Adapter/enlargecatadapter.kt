package com.unwiringapps.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.unwiringapps.wallpaperapp.CategoryenlargeActivity
import com.unwiringapps.wallpaperapp.FinalWallpaper
import com.unwiringapps.wallpaperapp.Model.thecatenlargeModel
import com.unwiringapps.wallpaperapp.R

class enlargecatadapter(
    val requiredContext: Context,
    val  listenlargeone: ArrayList<thecatenlargeModel>
) : RecyclerView.Adapter<enlargecatadapter.enlargecatholder>() {



    inner class enlargecatholder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imageviewbro = itemView.findViewById<RoundedImageView>(R.id.catenlargeitem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): enlargecatholder {
        return enlargecatholder(
            LayoutInflater.from(requiredContext).inflate(R.layout.items_activity_cat_enlarge, parent, false)
        )
    }

        override fun onBindViewHolder(holder: enlargecatholder, position: Int) {

        Glide.with(requiredContext).load(listenlargeone[position].link).into(holder.imageviewbro)
            holder.itemView.setOnClickListener {
            // itemView is the entire layout that we have created for repeated item {item_bom.xml}
            val intent = Intent (requiredContext , FinalWallpaper::class.java)
            intent.putExtra("link" , listenlargeone[position].link)
            requiredContext.startActivity(intent)
        }

    }

    override fun getItemCount() = listenlargeone.size

}