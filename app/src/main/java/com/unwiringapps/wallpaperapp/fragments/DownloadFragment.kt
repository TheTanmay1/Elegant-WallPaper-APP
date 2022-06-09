package com.unwiringapps.wallpaperapp.fragments

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unwiringapps.wallpaperapp.R
import com.unwiringapps.wallpaperapp.databinding.FragmentDownloadBinding
import java.io.File

class DownloadFragment : Fragment() {

    lateinit var binding : FragmentDownloadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDownloadBinding.inflate(layoutInflater, container, false)

         val allFiles : Array<File>
         val imageList = arrayListOf<String>()

// downloading not working for now

        val  targetPath = Environment.getExternalStorageDirectory().absolutePath+"/Pictures/ELEGANT Wallpaper"

        val targetFile = File(targetPath)
         allFiles = targetFile. listFiles()!!

        for (data in allFiles)
        {
            imageList.add(data.absolutePath)
        }



            return binding.root
    }



}