package com.wejo.oemapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.wejo.oemapp.R
import com.wejo.oemapp.activities.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @Class SplashActivity
 *
 * Displays the splash screen then starts app
 */
class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tv_tagline.animate().translationY(600f).setDuration(1000).start()
        Handler(Looper.getMainLooper()).postDelayed(runnable, 1000)
    }

    private val runnable = Runnable {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}