package com.bsuir.myquizwithfirebase.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bsuir.myquizwithfirebase.Singletons
import com.bsuir.myquizwithfirebase.databinding.ActivityMainBinding
import com.bsuir.myquizwithfirebase.model.Question
import com.bsuir.myquizwithfirebase.model.User
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        createQuestions()
        Singletons.init(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pager = binding.pager
        val pageAdapter: FragmentStateAdapter = PageAdapter(this)
        pager.adapter = pageAdapter

        val tabLayout: TabLayout = binding.tabLayout
        val tabLayoutMediator = TabLayoutMediator(tabLayout, pager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Меню"
                }
                1 -> {
                    tab.text = "Рейтинг"
                }
            }
        }
        tabLayoutMediator.attach()

    }

    private fun createQuestions() {
        database = Firebase.database.reference
        database.child("q")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val q0 = Question(
                        id = 0L,
                        question = "Какой язык программирования используется для разработки приложений под Android?",
                        answer1 = "Java",
                        answer2 = "Python",
                        answer3 = "JavaScript",
                        answer4 = "C#",
                        correctAnswer = "1",
                        url = "https://www.iguides.ru/upload/medialibrary/387/327qsorrhd1672gpifkd5ueegjz8qo9o.jpg"
                    )
                    database.child("q").child(q0.id.toString()).setValue(q0)
                    val q1 = Question(
                        id = 1L,
                        question = "Как называется интегрированная среда разработки для Android-приложений, предоставляющая широкие возможности для создания, тестирования и отладки приложений?",
                        answer1 = "NetBeans",
                        answer2 = "Visual Studio Code",
                        answer3 = "Android Studio",
                        answer4 = "Xcode",
                        correctAnswer = "2",
                        url = ""
                    )
                    database.child("q").child(q1.id.toString()).setValue(q1)
                    val q2 = Question(
                        id = 2L,
                        question = "Какое расширение используется для файлов макетов приложений Android?",
                        answer1 = ".json",
                        answer2 = ".xml",
                        answer3 = ".html",
                        answer4 = ".css",
                        correctAnswer = "2",
                        url = ""
                    )
                    database.child("q").child(q2.id.toString()).setValue(q2)
                    val q3 = Question(
                        id = 3L,
                        question = "Какое API необходимо использовать для получения геолокации устройства в приложении под Android?",
                        answer1 = "LocationManager",
                        answer2 = "Geocoder",
                        answer3 = "Navigation",
                        answer4 = "GPS",
                        correctAnswer = "1",
                        url = ""
                    )
                    database.child("q").child(q3.id.toString()).setValue(q3)
                    val q4 = Question(
                        id = 4L,
                        question = "Как называется основной файл манифеста приложения Android, содержащий информацию о приложении и его компонентах?",
                        answer1 = "MainActivity.java",
                        answer2 = "build.gradle",
                        answer3 = "styles.xml",
                        answer4 = "AndroidManifest.xml",
                        correctAnswer = "4",
                        url = ""
                    )
                    database.child("q").child(q4.id.toString()).setValue(q4)
                    val q5 = Question(
                        id = 5L,
                        question = "Как называется механизм, позволяющий взаимодействовать между разными компонентами приложения, а также с компонентами других приложений?",
                        answer1 = "Intent",
                        answer2 = "BroadcastReceiver",
                        answer3 = "Service",
                        answer4 = "AsyncTask",
                        correctAnswer = "1",
                        url = ""
                    )
                    database.child("q").child(q5.id.toString()).setValue(q5)
                    val q6 = Question(
                        id = 6L,
                        question = "Как называется компонент приложения, который обеспечивает доступ к базе данных SQLite, используемой для хранения данных приложения?",
                        answer1 = "Service",
                        answer2 = "BroadcastReceiver",
                        answer3 = "ContentProvider",
                        answer4 = "SQLiteHelper",
                        correctAnswer = "3",
                        url = ""
                    )
                    database.child("q").child(q6.id.toString()).setValue(q6)
                    val q7 = Question(
                        id = 7L,
                        question = "Как называется механизм, позволяющий работать с API и получать данные с веб-серверов в приложении Android?",
                        answer1 = "Retrofit",
                        answer2 = "Volley",
                        answer3 = "OkHttp",
                        answer4 = "Apache HttpComponents",
                        correctAnswer = "1",
                        url = ""
                    )
                    database.child("q").child(q7.id.toString()).setValue(q7)
                    val q8 = Question(
                        id = 8L,
                        question = "В каком файле проекта Android хранятся настройки Gradle и фактические зависимости проекта?",
                        answer1 = "build.gradle",
                        answer2 = "settings.gradle",
                        answer3 = "gradlew",
                        answer4 = "gradle.properties",
                        correctAnswer = "1",
                        url = ""
                    )
                    database.child("q").child(q8.id.toString()).setValue(q8)
                    val q9 = Question(
                        id = 9L,
                        question = "Какая архитектурная модель используется для разделения приложения на отдельные компоненты, с целью ускорения сборки и упрощения его тестирования?",
                        answer1 = "Model-View-Presenter (MVP)",
                        answer2 = "Model-View-ViewModel (MVVM)",
                        answer3 = "Model-Driven Architecture (MDA)",
                        answer4 = "Model-View-Controller (MVC)",
                        correctAnswer = "2",
                        url = ""
                    )
                    database.child("q").child(q9.id.toString()).setValue(q9)
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

}