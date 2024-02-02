package com.example.cse227.fetchAndSave

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.cse227.R

class Option : AppCompatActivity() {
    lateinit var fetchBtn : Button
    lateinit var saveBtn : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        fetchBtn = findViewById(R.id.fetchBtn)
        saveBtn = findViewById(R.id.saveBtn)

        saveBtn.setOnClickListener {
            saveToDatabase()
        }

        fetchBtn.setOnClickListener {
            fetchFromDatabase()
        }
    }

    private fun fetchFromDatabase() {
        val intent = Intent(this , Fetch::class.java)
        startActivity(intent)

    }

    private fun saveToDatabase() {
        val intent = Intent(this , Save::class.java)
        startActivity(intent)
    }
}