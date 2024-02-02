package com.example.cse227.loginSignup

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cse227.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginSignupMain : AppCompatActivity() {
    lateinit var a: FirebaseAuth
    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: Button
    lateinit var db :DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup_main)

        emailEt = findViewById(R.id.email)
        passwordEt = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginBtn)
        var signupLink = findViewById<TextView>(R.id.signupLink)

        signupLink.setOnClickListener {
            val intent = Intent(this , Signup::class.java)
            startActivity(intent)
        }

        a = FirebaseAuth.getInstance()
        db = Firebase.database.reference
        db.child("K202").child("CSE227")
            .setValue("UNIT1")
        db.child("CustomerMyApp")
            .child("Name").child("ContactDetails")
            .child("PersonalNumber").setValue("1237723727")
        db.child("CustomerMyApp").child("Name")
            .child("ContactDetails").child("officialNumber").setValue("8988777")
        signupLink.setOnClickListener {
//            val userEmail = emailEt.text.toString()
//            val userPassword = passwordEt.text.toString()

            a.createUserWithEmailAndPassword("amartyasen895@gmail.com", "abc@12345")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        a.currentUser?.sendEmailVerification()?.addOnCompleteListener { verificationTask ->
                            if (verificationTask.isSuccessful) {
                                Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(
                                        this,
                                        "Not sent and signup not done" + verificationTask.exception,
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                                this,
                                "Signup Failed" + task.exception,
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        loginBtn.setOnClickListener {
            val userEmail = emailEt.text.toString()
            val userPassword = passwordEt.text.toString()
            login(userEmail, userPassword)
        }
    }

    private fun login(user: String, pass: String) {
        a.signInWithEmailAndPassword(user, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
