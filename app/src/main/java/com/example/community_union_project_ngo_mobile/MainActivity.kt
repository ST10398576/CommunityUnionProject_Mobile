package com.example.community_union_project_ngo_mobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.ui.onboarding.SplashActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Redirect to the OnboardingActivity
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish()
    }
}
