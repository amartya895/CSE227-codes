package com.example.cse227.loginSignup

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cse227.MainActivity
import com.example.cse227.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {

    lateinit var etEmail :EditText
    private lateinit var etName : EditText
    private lateinit var etPhone : EditText
    lateinit var etAddress: EditText
    lateinit var etPass : EditText
    lateinit var etConfPass:EditText
    lateinit var btnSignup : Button
    lateinit var firebaseDatabase:FirebaseDatabase
    lateinit var userDetail:UserDetails
    lateinit var databaseReference : DatabaseReference
    lateinit var auth:FirebaseAuth
    lateinit var tvRedirectLogin: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        etEmail = findViewById(R.id.setEmail)
        etName = findViewById(R.id.setUserName)
        etPhone = findViewById(R.id.setPhoneNumber)
        etAddress = findViewById(R.id.setAddress)
        etPass = findViewById(R.id.setPass)
        etConfPass = findViewById(R.id.setConfirmPass)
        btnSignup = findViewById(R.id.setSignupBtn)
        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.getReference("UserDetails")
        auth = FirebaseAuth.getInstance()
        userDetail= UserDetails()
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)
        btnSignup.setOnClickListener {
            signup()
        }

        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this , LoginSignupMain::class.java)
            startActivity(intent)
        }
    }


    private  fun signup(){
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confPass = etConfPass.text.toString()
        val n = etName.text.toString()
        val add = etAddress.text.toString()
        val phone  = etPhone.text.toString()

        if (email.isBlank() || pass.isBlank() || confPass.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confPass) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email , pass)
            .addOnCompleteListener(this){
                if (it.isSuccessful)
                {
                    Toast.makeText(this , "Successfully Signup" , Toast.LENGTH_SHORT).show()
                    addDataToFirebase(n ,phone , add )
                    val intent = Intent(this , MainActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText( this, "Signup failed" , Toast.LENGTH_SHORT).show()
                }
            }

    }


    private fun addDataToFirebase(n:String , c:String , a:String)
    {
            userDetail.setEmployeeName(n)
            userDetail.setEmployeeContactNumber(c)
            userDetail.setEmployeeAddress(a)
        val postListener = object  : ValueEventListener
        {
            override fun onDataChange(snapshot : DataSnapshot) {
                databaseReference.setValue(userDetail)
                Toast.makeText(applicationContext , "Data Added" , Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error : DatabaseError) {
               Log.w(TAG ,"LoadPost:onCancelled" , error.toException() )
                Toast.makeText( applicationContext, "Fail to add data" , Toast.LENGTH_SHORT).show()
            }

        }

        databaseReference.addValueEventListener(postListener)
    }
}