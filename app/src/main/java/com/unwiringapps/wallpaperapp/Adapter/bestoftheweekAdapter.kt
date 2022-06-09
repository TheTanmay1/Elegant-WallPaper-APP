package com.unwiringapps.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unwiringapps.wallpaperapp.FinalWallpaper
import com.unwiringapps.wallpaperapp.Model.bestoftheweekModel
import com.unwiringapps.wallpaperapp.R

class bestoftheweekAdapter(
    val requireContext: Context,
    val listoftheweek: ArrayList<bestoftheweekModel>
) : RecyclerView.Adapter<bestoftheweekAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.bom_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_bom, parent, false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {


        Glide.with(requireContext).load(listoftheweek[position].link).into(holder.imageView)

        holder.itemView.setOnClickListener {
            // itemView is the entire layout that we have created for repeated item {item_bom.xml}
            val intent = Intent (requireContext , FinalWallpaper::class.java)

            intent.putExtra("link" , listoftheweek[position].link)
            requireContext.startActivity(intent)
        }

    }

    override fun getItemCount() = listoftheweek.size

}

