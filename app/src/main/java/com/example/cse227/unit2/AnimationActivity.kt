package com.example.cse227.unit2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.cse227.R

class AnimationActivity : AppCompatActivity() {
    lateinit var  iv :ImageView
    lateinit var animeBtn : Button
    lateinit var animBlink:Animation
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        iv = findViewById(R.id.ivAnime)
        animeBtn = findViewById(R.id.animBtn)

        animeBtn.setOnClickListener {

            animBlink = AnimationUtils.loadAnimation(applicationContext , R.anim.sequential)
            iv.startAnimation(animBlink)
        }
//        val s = AlphaAnimation(0.0f,1.0f)
//        s.duration = 50
//        s.repeatMode = Animation.REVERSE
//        s.repeatCount = Animation.INFINITE
//        s.startOffset = 20
//
//        iv.startAnimation(s)
    }
}