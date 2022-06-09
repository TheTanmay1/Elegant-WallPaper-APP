package com.unwiringapps.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.unwiringapps.wallpaperapp.FinalWallpaper
import com.unwiringapps.wallpaperapp.Model.thecolortoneModel
import com.unwiringapps.wallpaperapp.R

class colortoneAdapter(val requireContext: Context, val listthecolortone: ArrayList<thecolortoneModel>) : RecyclerView.Adapter<colortoneAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
           val cardViewbro = itemView.findViewById<CardView>(R.id.color_box)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
       return bomViewHolder(
           LayoutInflater.from(requireContext).inflate(R.layout.item_colortone, parent,false)
       )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {

        val colorsbro = listthecolortone[position].color
        val colordude: Int = Color.parseColor(colorsbro!!)  // to parse string into int
        holder.cardViewbro.setCardBackgroundColor(colordude)

        holder.itemView.setOnClickListener {
            // itemView is the entire layout that we have created for repeated item {item_bom.xml}
            val intent = Intent (requireContext , FinalWallpaper::class.java)

            intent.putExtra("link" , listthecolortone[position].link)
            requireContext.startActivity(intent)
        }


    }

    override fun getItemCount() = listthecolortone.size

    }

