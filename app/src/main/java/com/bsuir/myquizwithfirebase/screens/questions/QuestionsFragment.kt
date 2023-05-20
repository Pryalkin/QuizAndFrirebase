package com.bsuir.myquizwithfirebase.screens.questions

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.StrictMode
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bsuir.myquizwithfirebase.R
import com.bsuir.myquizwithfirebase.databinding.FragmentQuestionsBinding
import com.bsuir.myquizwithfirebase.model.User
import com.bsuir.myquizwithfirebase.screens.main.MainActivity
import com.bsuir.myquizwithfirebase.views.BasicViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.IOException
import java.net.URL
import kotlin.properties.Delegates


class QuestionsFragment : Fragment() {

    private var pageNumber by Delegates.notNull<Int>()
    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var database: DatabaseReference
    private lateinit var correctAnswer: String
    private val viewModel by viewModels<BasicViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = if (arguments != null) requireArguments().getInt("num") else 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentQuestionsBinding.inflate(inflater)
        methodForLogic()
        return binding.root
    }

    private fun methodForLogic() {
        binding.apply {
            if (pageNumber < 10) {
                completeTheGame.visibility = View.GONE
                result.visibility = View.GONE
                methodGroupForQuestions()
            } else {
                answers.visibility = View.GONE
                questionText.visibility = View.GONE
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                try {
                    val url = URL("https://characterinthemaking.files.wordpress.com/2018/12/the-end.jpg")
                    val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    binding.imageQuestion.setImageBitmap(image)
                } catch (e: IOException) {
                    System.out.println(e)
                }
                methodForTotalFragment()
            }
        }
    }

    private fun methodForTotalFragment() {
        val username = viewModel.getUsername()
        val res = viewModel.getResult()
        binding.result.text = "$username ваш результат: $res"
        binding.completeTheGame.setOnClickListener{
            database = Firebase.database.reference
            database.child("users")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val user = User(username, res)
                        database.child("users").child(dataSnapshot.childrenCount.toString()).setValue(user)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun methodGroupForQuestions() {
        getQuestion()
        listenerForAnswerButtons()
    }

    private fun getQuestion() {
        database = Firebase.database.reference
        database.child("q").child(pageNumber.toString()).get().addOnSuccessListener {
            binding.apply {
                questionText.text = it.child("question").value.toString()
                b1.text =  it.child("answer1").value.toString()
                b2.text =  it.child("answer2").value.toString()
                b3.text =  it.child("answer3").value.toString()
                b4.text =  it.child("answer4").value.toString()
                correctAnswer = it.child("correctAnswer").value.toString()
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                try {
                    if(it.child("url").value.toString() != ""){
                        binding.imageQuestion.visibility = View.VISIBLE
                        val url = URL(it.child("url").value.toString())
                        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                        binding.imageQuestion.setImageBitmap(image)
                    } else binding.imageQuestion.visibility = View.GONE
                } catch (e: IOException) {
                    System.out.println(e)
                }
            }
        }
    }

    private fun listenerForAnswerButtons() {
        binding.apply {
            b1.setOnClickListener {
                switchToNewFragment(1)
                SystemClock.sleep(500)
                goToANewPage()
            }
            b2.setOnClickListener {
                switchToNewFragment(2)
                SystemClock.sleep(500)
                goToANewPage()
            }
            b3.setOnClickListener {
                switchToNewFragment(3)
                SystemClock.sleep(500)
                goToANewPage()
            }
            b4.setOnClickListener {
                switchToNewFragment(4)
                SystemClock.sleep(500)
                goToANewPage()
            }
        }
    }

    private fun switchToNewFragment(i: Int) {
        if (correctAnswer.toInt() == i) {
            val res = viewModel.getResult() + 1
            viewModel.setResult(res)
            Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
    }

    private fun goToANewPage() {
        val pager = requireActivity().findViewById<ViewPager2>(R.id.pager)
        val pageAdapter: FragmentStateAdapter = QuestionsAdapter(requireActivity())
        pager.setCurrentItem(++pageNumber, false)
        pager.adapter = pageAdapter
    }

        companion object {
            @JvmStatic
        fun newInstance(page: Int): QuestionsFragment {
            val fragment: QuestionsFragment = QuestionsFragment()
            val args = Bundle()
            args.putInt("num", page)
            fragment.arguments = args
            return fragment
        }
    }

}