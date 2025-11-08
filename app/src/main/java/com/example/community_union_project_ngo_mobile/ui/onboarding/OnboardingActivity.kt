package com.example.community_union_project_ngo_mobile.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.adapters.OnboardingAdapter
import com.example.community_union_project_ngo_mobile.models.OnboardingItem
import com.example.community_union_project_ngo_mobile.ui.admin.AdminLoginActivity
import com.example.community_union_project_ngo_mobile.ui.agent.FieldAgentLoginActivity
import com.example.community_union_project_ngo_mobile.ui.ngo.NgoLoginActivity
import com.example.community_union_project_ngo_mobile.ui.user.UserLoginActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)

        val items = listOf(
            OnboardingItem(
                R.drawable.user_img,
                "Become a User to help the community and donate...",
                "Sign Up"
            ),
            OnboardingItem(
                R.drawable.field_agent_img,
                "Become a Field Agent to help those in need...",
                "Sign Up"
            ),
            OnboardingItem(
                R.drawable.ngo_img,
                "If you are an NGO, join us to help bring change...",
                "Sign Up"
            ),
            OnboardingItem(
                R.drawable.ic_phoenix_logo,
                "Admin login page — accessible only to admins.",
                "Login"
            )
        )

        viewPager.adapter = OnboardingAdapter(items) { position ->
            when (position) {
                0 -> startActivity(Intent(this, UserLoginActivity::class.java))
                1 -> startActivity(Intent(this, FieldAgentLoginActivity::class.java))
                2 -> startActivity(Intent(this, NgoLoginActivity::class.java))
                3 -> startActivity(Intent(this, AdminLoginActivity::class.java))
            }
        }
    }
}
