package com.example.cse227.unit2.customcanvas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        val customFanView = CustomViewFan(this);
        setContentView(customFanView)
    }
}