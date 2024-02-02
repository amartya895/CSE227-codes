package com.example.cse227.fetchAndSave

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cse227.R

class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var tvEmpId:TextView
    private lateinit var tvEmpName:TextView
    private lateinit var tvEmpAge:TextView
    private lateinit var tvEmpSalary:TextView
    private lateinit var btnUpdate:Button
    private lateinit var btnDelete:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        tvEmpId = findViewById(R.id.tvEmpId)
        tvEmpName = findViewById(R.id.tvEmpName)
        tvEmpAge = findViewById(R.id.tvEmpAge)
        tvEmpSalary = findViewById(R.id.tvEmpSalary)

        setValuesToViews()

    }

    private fun setValuesToViews() {
        tvEmpId.text = intent.getStringExtra("empId")
        tvEmpName.text = intent.getStringExtra("empName")
        tvEmpAge.text = intent.getStringExtra("empAge")
        tvEmpSalary.text = intent.getStringExtra("empSalary")

    }
}