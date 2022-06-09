package com.unwiringapps.wallpaperapp

import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.unwiringapps.wallpaperapp.databinding.ActivityFinalWallpaperBinding
import com.unwiringapps.wallpaperapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.lang.Exception
import java.net.URL
import java.util.*

class FinalWallpaper : AppCompatActivity() {

    lateinit var binding: ActivityFinalWallpaperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinalWallpaperBinding.inflate(layoutInflater)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(binding.root)


        val urlbruh = intent.getStringExtra("link")

        val urlImage =
            URL(urlbruh)

        Glide.with(this).load(urlbruh).into(binding.finalwallpaper)


        binding.setwallpaperbtn.setOnClickListener {

            Toast.makeText(this, "Wallpaper Succesfully Set", Toast.LENGTH_SHORT).show()

            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()

            }

            GlobalScope.launch(Dispatchers.Main) {
                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                wallpaperManager.setBitmap(result.await())
            }


        }



        binding.dwnldbtn.setOnClickListener {
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
                
            }
            GlobalScope.launch(Dispatchers.Main) {
                saveImage(result.await())
            }

        }


    }

    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            Log.e(TAG, "toBitmap: exception", e)
            null
        }

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveImage(image: Bitmap?) {
        Log.d(TAG, "saveImage: image is ${image == null} $image")

        val random1 = Random().nextInt(520985)
        val random2 = Random().nextInt(952663)

        val name = "Amoled-${random1 + random2}"

        val data: OutputStream
        try {
            val resolver = contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + "Amoled Wallpaper"
            )
            val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            Log.d(TAG, "saveImage: image uri is $imageUri")
            data = resolver.openOutputStream(imageUri!!)!!
            image?.compress(Bitmap.CompressFormat.JPEG, 100, data)
            Objects.requireNonNull<OutputStream?>(data)
            Toast.makeText(this, "Image Save", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Log.e(TAG, "saveImage: exeption eccurred ${e.message}", e)
            Toast.makeText(this, "Image Not Save", Toast.LENGTH_SHORT).show()
        }

        
    }

    companion object {
        private const val TAG = "FinalWallpaper"
    }

}