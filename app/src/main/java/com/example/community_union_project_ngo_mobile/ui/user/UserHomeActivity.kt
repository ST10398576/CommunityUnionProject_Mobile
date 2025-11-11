package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityUserHomeBinding

class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USER_NAME")
        binding.tvWelcome.text = "Welcome\n${username}"

        binding.btnContactAgent.setOnClickListener {
            val intent = Intent(this, AgentInformationActivity::class.java)
            startActivity(intent)
        }

        binding.btnPartnerNgo.setOnClickListener {
            val intent = Intent(this, PartnerNgoListActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoFundMe.setOnClickListener {
            val intent = Intent(this, GoFundMeActivity::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, UserSettingsActivity::class.java)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }
}