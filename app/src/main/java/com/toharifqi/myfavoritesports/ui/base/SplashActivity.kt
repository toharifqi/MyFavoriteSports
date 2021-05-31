package com.toharifqi.myfavoritesports.ui.base

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.toharifqi.myfavoritesports.R
import com.toharifqi.myfavoritesports.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animFadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_fadein)

        animFadeIn.setAnimationListener(this)

        binding.mainIcon.visibility = View.VISIBLE
        binding.mainIcon.startAnimation(animFadeIn)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(context, R.color.title_text_color)
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onAnimationStart(animation: Animation?) {}

    override fun onAnimationEnd(animation: Animation?) {
        finish()
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    }

    override fun onAnimationRepeat(animation: Animation?) {}
}