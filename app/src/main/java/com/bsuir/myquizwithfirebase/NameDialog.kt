package com.bsuir.myquizwithfirebase

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

class NameDialog: DialogFragment() {

    private lateinit var et: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        @SuppressLint("InflateParams") val root: View = inflater.inflate(R.layout.name_dialog, null)
        et = root.findViewById<EditText>(R.id.name_dialog_in)
        return builder
            .setView(root)
            .setTitle("Имя игрока")
            .setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
                Result.result = 0
                Result.username = et.getText().toString()
                val intent = Intent(context, QuestionsActivity::class.java)
                startActivity(intent)
                Toast.makeText(activity, "Hello " + Result.username, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .create()
    }


}