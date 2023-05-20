package com.bsuir.myquizwithfirebase.screens.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.bsuir.myquizwithfirebase.R
import com.bsuir.myquizwithfirebase.screens.questions.QuestionsActivity
import com.bsuir.myquizwithfirebase.views.BasicViewModel

class NameDialog: DialogFragment() {

    private lateinit var et: EditText
    private val viewModel by viewModels<BasicViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        @SuppressLint("InflateParams") val root: View = inflater.inflate(R.layout.name_dialog, null)
        et = root.findViewById<EditText>(R.id.name_dialog_in)
        return builder
            .setView(root)
            .setTitle("Имя игрока")
            .setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
                val username = et.getText().toString()
                viewModel.setUsername(username)
                viewModel.setResult(0)
                val intent = Intent(context, QuestionsActivity::class.java)
                startActivity(intent)
                Toast.makeText(activity, "Hello $username", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .create()
    }


}