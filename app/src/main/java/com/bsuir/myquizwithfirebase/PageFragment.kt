package com.bsuir.myquizwithfirebase

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsuir.myquizwithfirebase.databinding.FragmentPageBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type
import java.net.URL
import kotlin.properties.Delegates

class PageFragment : Fragment() {

    private var pageNumber by Delegates.notNull<Int>()
    private lateinit var binding: FragmentPageBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = if (arguments != null) requireArguments().getInt("num") else 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPageBinding.inflate(inflater)

        when (pageNumber) {
            0 -> {
                initViewForMenu()
                binding.mainPlay.setOnClickListener {
                    val dialog = NameDialog()
                    dialog.show(requireActivity().supportFragmentManager, null)
                }
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                try {
                    val url = URL("https://ggkjt.bsut.by/wp-content/uploads//2021/02/sova-1.jpg")
                    val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    binding.image.setImageBitmap(image)
                } catch (e: IOException) {
                    System.out.println(e)
                }
            }
            1 -> {
                initViewForResult()
                adapter = UserAdapter()
                val database = Firebase.database.reference
                database.child("users").get().addOnSuccessListener {
                    val gson = Gson()
                    val userListType: Type = object : TypeToken<List<User?>?>() {}.type
                    val userArray: List<User> = gson.fromJson(it.value.toString(), userListType)
                    adapter.users = userArray
                    val layoutManager  = LinearLayoutManager(context)
                    binding.recView.layoutManager = layoutManager
                    binding.recView.adapter = adapter
                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
                }
            }
        return binding.root
    }

    private fun initViewForMenu() {
        binding.apply {
            image.visibility = View.VISIBLE
            mainPlay.visibility = View.VISIBLE
            recView.visibility = View.GONE
        }
    }

    private fun initViewForResult() {
        binding.apply {
            image.visibility = View.GONE
            mainPlay.visibility = View.GONE
            recView.visibility = View.VISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(page: Int): PageFragment {
            val fragment: PageFragment = PageFragment()
            val args = Bundle()
            args.putInt("num", page)
            fragment.arguments = args
            return fragment
        }
    }
}