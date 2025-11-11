package com.example.communityunionproject_mobile.ui.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.communityunionproject_mobile.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Admin Dashboard"

        binding.btnManageUsers.setOnClickListener {
            startActivity(Intent(this, ManageUsersActivity::class.java))
        }

        binding.btnManageNgos.setOnClickListener {
            startActivity(Intent(this, ManageNgoActivity::class.java))
        }

        binding.btnManageFieldAgents.setOnClickListener {
            startActivity(Intent(this, ManageFieldAgentsActivity::class.java))
        }

        binding.btnAdminReports.setOnClickListener {
            startActivity(Intent(this, AdminReportsActivity::class.java))
        }

        binding.btnAdminSettings.setOnClickListener {
            startActivity(Intent(this, AdminSettingsActivity::class.java))
        }
    }
}
