package com.example.cse227.unit2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.cse227.MainActivity
import com.example.cse227.R

class SplashScreen : AppCompatActivity() {
    lateinit var ivSplash : ImageView
    lateinit var animSplash : Animation

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        ivSplash=findViewById(R.id.ivSplash)

        animSplash=AnimationUtils.loadAnimation(applicationContext , R.anim.blinkdemo)

        ivSplash.startAnimation(animSplash)

        Handler().postDelayed({
                                  val intent=Intent(this , MainActivity::class.java)
                                  startActivity(intent)
                                  finish()
                              } , 3000)
    }
}