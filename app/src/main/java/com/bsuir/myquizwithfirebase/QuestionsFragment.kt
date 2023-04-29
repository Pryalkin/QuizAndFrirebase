package com.bsuir.myquizwithfirebase

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bsuir.myquizwithfirebase.Result.Companion.result
import com.bsuir.myquizwithfirebase.Result.Companion.username
import com.bsuir.myquizwithfirebase.databinding.FragmentQuestionsBinding
import com.bsuir.myquizwithfirebase.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.properties.Delegates


class QuestionsFragment : Fragment() {

    private var pageNumber by Delegates.notNull<Int>()
    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var database: DatabaseReference
    private lateinit var correctAnswer: String

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
                buttonHelp.visibility = View.GONE
                buttonHelpFriend.visibility = View.GONE
                answers.visibility = View.GONE
                questionText.visibility = View.GONE
                requireActivity().findViewById<View>(R.id.timer).visibility = View.GONE
                methodForTotalFragment()
            }
            if (btnHelp == 3) {
                buttonHelp.visibility = View.GONE
            }
            if (btnHelpFriend == 1) {
                buttonHelpFriend.visibility = View.GONE
            }
        }
    }

    private fun methodForTotalFragment() {
        binding.result.text = Result.username + " ваш результат: " + Result.result
        binding.completeTheGame.setOnClickListener{
            database = Firebase.database.reference
            database.child("users")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val user = User(username, result)
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
        listenerPerButton50by50()
        listenerForFriendHelpButton()
    }

    private fun listenerForFriendHelpButton() {
        binding.buttonHelpFriend.setOnClickListener{
            when (correctAnswer.toInt()) {
                1 -> binding.button1.setTextColor(Color.GREEN)
                2 -> binding.button2.setTextColor(Color.GREEN)
                3 -> binding.button3.setTextColor(Color.GREEN)
                4 -> binding.button4.setTextColor(Color.GREEN)
            }
            binding.buttonHelpFriend.visibility = View.INVISIBLE
            btnHelpFriend++
        }
    }

    private fun listenerPerButton50by50() {
        binding.buttonHelp.setOnClickListener{
                if (btnHelp < 3) {
                    val numberOfAttempts = IntArray(3)
                    run {
                        var i = 0
                        while (i < numberOfAttempts.size) {
                            var flag = false
                            val randomButton = (Math.random() * 4 + 1).toInt()
                            for (y in numberOfAttempts.indices) {
                                if (numberOfAttempts[y] == randomButton) {
                                    i--
                                    flag = true
                                }
                            }
                            if (!flag) numberOfAttempts[i] = randomButton
                            i++
                        }
                    }
                    var i = 0
                    for (z in numberOfAttempts.indices) {
                        if (correctAnswer.toInt() != numberOfAttempts[z] && i < 2) {
                            if (numberOfAttempts[z] == 1) binding.button1.visibility = View.INVISIBLE
                            if (numberOfAttempts[z] == 2) binding.button2.visibility = View.INVISIBLE
                            if (numberOfAttempts[z] == 3) binding.button3.visibility = View.INVISIBLE
                            if (numberOfAttempts[z] == 4) binding.button4.visibility = View.INVISIBLE
                            i++
                        }
                    }
                    btnHelp++
                }
                binding.buttonHelp.visibility = View.INVISIBLE
            }
    }

    private fun getQuestion() {
        database = Firebase.database.reference
        database.child("questions").child(pageNumber.toString()).get().addOnSuccessListener {
            binding.apply {
                questionText.text = it.child("question").value.toString()
                button1.text =  it.child("answer1").value.toString()
                button2.text =  it.child("answer2").value.toString()
                button3.text =  it.child("answer3").value.toString()
                button4.text =  it.child("answer4").value.toString()
                correctAnswer = it.child("correctAnswer").value.toString()
            }
        }
    }

    private fun listenerForAnswerButtons() {
        binding.answers.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.button1 -> {
                    switchToNewFragment(1)
                    SystemClock.sleep(500)
                    goToANewPage()
                }
                R.id.button2 -> {
                    switchToNewFragment(2)
                    SystemClock.sleep(500)
                    goToANewPage()
                }
                R.id.button3 -> {
                    switchToNewFragment(3)
                    SystemClock.sleep(500)
                    goToANewPage()
                }
                R.id.button4 -> {
                    switchToNewFragment(4)
                    SystemClock.sleep(500)
                    goToANewPage()
                }
                else -> {}
            }
        })
    }

    private fun switchToNewFragment(i: Int) {
        if (correctAnswer.toInt() == i) {
            Result.result++
            Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
    }

    private fun goToANewPage() {
        val pager = requireActivity().findViewById<ViewPager2>(R.id.pager)
        val pageAdapter: FragmentStateAdapter = MyAdapter(requireActivity())
        pager.setCurrentItem(++pageNumber, false)
        pager.adapter = pageAdapter
    }

        companion object {
            private var btnHelp = 0
            private var btnHelpFriend = 0

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