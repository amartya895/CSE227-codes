package com.example.cse227.otpVerification

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cse227.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider


class VerifyOtp : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        auth = FirebaseAuth.getInstance()

        val storedVerificationId = intent.getStringExtra("storedVerificationId")

        findViewById<Button>(R.id.verifyOtpBtn).setOnClickListener {
            val otp = findViewById<EditText>(R.id.enterOtpEt).text.trim().toString()
            if(otp.isNotEmpty())
            {
                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        storedVerificationId.toString(),otp
                )
                signInWithPhoneAuthCredential(credential)
            }
            else{
                Toast.makeText(this , "enter Otp" , Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun signInWithPhoneAuthCredential(credential : PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){
                if(it.isSuccessful)
                {
                    val intent = Intent(this , OtpMain::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    if(it.exception is FirebaseAuthInvalidCredentialsException)
                    {
                        Toast.makeText(this , "Invalid Otp" , Toast.LENGTH_SHORT).show()
                    }
                }

            }

    }
}