package com.unwiringapps.wallpaperapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.unwiringapps.wallpaperapp.Adapter.bestoftheweekAdapter
import com.unwiringapps.wallpaperapp.Adapter.categoriesAdapter
import com.unwiringapps.wallpaperapp.Adapter.colortoneAdapter
import com.unwiringapps.wallpaperapp.Model.bestoftheweekModel
import com.unwiringapps.wallpaperapp.Model.thecategoriesModel
import com.unwiringapps.wallpaperapp.Model.thecolortoneModel
import com.unwiringapps.wallpaperapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var dbbro : FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment_home, container, false)


         dbbro = FirebaseFirestore.getInstance()

        dbbro.collection("best of the week").addSnapshotListener { value, error ->

            val listoftheweek = arrayListOf<bestoftheweekModel>()
            val data = value?.toObjects(bestoftheweekModel::class.java) // jo value firebase se milli
            // ussi data ke under store kerna hoga and then uske baad usko ek list mae store kerna parega
            listoftheweek.addAll(data!!)

            binding.rcvBom.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

            binding.rcvBom.adapter = bestoftheweekAdapter(requireContext(), listoftheweek)

        }


        dbbro.collection("the color tone").addSnapshotListener { value, error ->

          val listthecolortone = arrayListOf<thecolortoneModel>()
            val data = value?.toObjects(thecolortoneModel::class.java)
            listthecolortone.addAll((data!!))

            binding.rcvTct.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            binding.rcvTct.adapter = colortoneAdapter(requireContext(), listthecolortone)


        }


        dbbro.collection("Categories").addSnapshotListener { value, error ->

            val listthecategories = arrayListOf<thecategoriesModel>()
            val data = value?.toObjects(thecategoriesModel::class.java)
            listthecategories.addAll((data!!))

            binding.rcvCat.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvCat.adapter = categoriesAdapter(requireContext(), listthecategories)



        }


         return binding.root

    }



}