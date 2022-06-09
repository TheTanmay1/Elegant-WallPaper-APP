package com.unwiringapps.wallpaperapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.unwiringapps.wallpaperapp.Adapter.enlargecatadapter
import com.unwiringapps.wallpaperapp.Model.thecatenlargeModel
import com.unwiringapps.wallpaperapp.databinding.ActivityCategoryenlargeBinding
import java.net.URL

class CategoryenlargeActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryenlargeBinding


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryenlargeBinding.inflate(layoutInflater)


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        setContentView(binding.root)


//        val urlbruh = intent.getStringExtra("vid")

//        val urlImage =
//            URL(urlbruh)


//        Glide.with(this).load(urlbruh).into(findViewById(R.id.catenlargeitem))


        val dbbro = FirebaseFirestore.getInstance()
        val vid = intent.getStringExtra("vid")//this is gettinh image but you need id here udenrstand?
        val name = intent.getStringExtra("name")

//        Log.e("@@@@", "onCreate: "+vid )



                dbbro.collection("Categories").document(vid!!).collection("nature wallpaper")
                    .addSnapshotListener { value, error ->


                        val listenlargeone = arrayListOf<thecatenlargeModel>()
                        val data = value?.toObjects(thecatenlargeModel::class.java)
                        listenlargeone.addAll((data!!))

                        binding.catkanaam.text = name.toString()

                        var rohan = listenlargeone.size.toString()
                        binding.catcount.text = "$rohan Wallpapers Available"


                            binding.catkarecycle.layoutManager =
                            StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL)

                        binding.catkarecycle.adapter = enlargecatadapter(this, listenlargeone)


                    }


            }
    }

