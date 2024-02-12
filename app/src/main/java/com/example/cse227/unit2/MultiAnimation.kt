package com.example.cse227.unit2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.cse227.R

class MultiAnimation : AppCompatActivity() {
    lateinit var boyIv :ImageView
    lateinit var girlIv : ImageView
    lateinit var  boyBtn : Button
    lateinit var  girlBtn : Button
    private lateinit var girlAni :Animation
    private lateinit var boyAni :Animation

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_animation)

        boyIv = findViewById(R.id.ivBoy)
        girlIv = findViewById(R.id.ivGirl)

        boyBtn = findViewById(R.id.btnBoyAni)
        girlBtn = findViewById(R.id.btnGirlAni)

        girlBtn.setOnClickListener {
            girlAni = AnimationUtils.loadAnimation(applicationContext , R.anim.sequential)
            girlIv.startAnimation(girlAni)
        }

        boyBtn.setOnClickListener {
            boyAni = AnimationUtils.loadAnimation(applicationContext , R.anim.move)
            boyIv.startAnimation(boyAni)
        }




    }
}