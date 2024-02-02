package com.example.cse227.fetchAndSave

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cse227.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Save : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var etSalary: EditText
    private lateinit var saveBtn: Button
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        etSalary = findViewById(R.id.etSalary)
        saveBtn = findViewById(R.id.btnSave)


        saveBtn.setOnClickListener {
            saveToDatabase()
        }
    }

    private fun saveToDatabase() {
        val empName = etName.text.toString()
        val empAge = etAge.text.toString()
        val empSalary = etSalary.text.toString()

        if (empName.isEmpty()) {
            etName.error = "Please enter name"
            return
        }
        if (empAge.isEmpty()) {
            etAge.error = "Please enter age"
            return
        }
        if (empSalary.isEmpty()) {
            etSalary.error = "Please enter salary"
            return
        }
        dbRef = FirebaseDatabase.getInstance().getReference("Employees")


        val empId = dbRef.push().key!!

        val employee = EmployeeModel(empId, empName, empAge, empSalary)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                    etName.text.clear()
                    etAge.text.clear()
                    etSalary.text.clear()
                } else {
                    Toast.makeText(this, "Error: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
