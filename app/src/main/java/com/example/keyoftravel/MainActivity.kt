package com.example.keyoftravel

import android.content.Intent
import com.example.keyoftravel.R
import android.os.Bundle
import android.os.Handler
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebViewClient


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity, MenuActivity::class.java))
            finish()
        },4000)
    }
}