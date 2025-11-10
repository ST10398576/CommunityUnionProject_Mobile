package com.example.community_union_project_ngo_mobile.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.adapters.OnboardingAdapter
import com.example.community_union_project_ngo_mobile.models.OnboardingItem
import com.example.community_union_project_ngo_mobile.ui.admin.AdminLoginActivity
import com.example.community_union_project_ngo_mobile.ui.agent.FieldAgentRegisterActivity
import com.example.community_union_project_ngo_mobile.ui.ngo.NgoRegisterActivity
import com.example.community_union_project_ngo_mobile.ui.user.UserRegisterActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Timer
import java.util.TimerTask

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private var currentPage = 0
    private var timer: Timer? = null
    private val DELAY_MS: Long = 500
    private val PERIOD_MS: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        val items = listOf(
            OnboardingItem(
                R.drawable.user_img,
                "Become a User to help the community and donate for a good course, even a little makes a big difference.",
                "Sign Up"
            ),
            OnboardingItem(
                R.drawable.field_agent_img,
                "Become a Field Agent to help those in need during desperate times, eliminate poverty in the heart of the city",
                "Sign Up"
            ),
            OnboardingItem(
                R.drawable.ngo_img,
                "If you are an NGO, join us and help the community to bring about a change for a better country today or tomorrow.",
                "Sign Up"
            ),
            OnboardingItem(
                R.drawable.ic_phoenix_logo,
                "Admin Login Page Accessible only to admins of the Application",
                "Login"
            )
        )

        viewPager.adapter = OnboardingAdapter(items) { position ->
            when (position) {
                0 -> startActivity(Intent(this, UserRegisterActivity::class.java))
                1 -> startActivity(Intent(this, FieldAgentRegisterActivity::class.java))
                2 -> startActivity(Intent(this, NgoRegisterActivity::class.java))
                3 -> startActivity(Intent(this, AdminLoginActivity::class.java))
            }
        }

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            if (currentPage == items.size) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)
    }
}