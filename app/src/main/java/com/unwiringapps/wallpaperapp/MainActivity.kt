package com.unwiringapps.wallpaperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.unwiringapps.wallpaperapp.databinding.ActivityMainBinding
import com.unwiringapps.wallpaperapp.fragments.DownloadFragment
import com.unwiringapps.wallpaperapp.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) // these 2 lines are used to remove status bar


        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        replaceFragment(HomeFragment())


        binding.hmeimg1.setOnClickListener{
       replaceFragment(HomeFragment())
        }

        binding.dwnldimg2.setOnClickListener{
            replaceFragment(DownloadFragment())
        }




    }

    fun replaceFragment ( Fragment : Fragment)
    {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentreplace, Fragment)
        transaction.commit()
    }

}