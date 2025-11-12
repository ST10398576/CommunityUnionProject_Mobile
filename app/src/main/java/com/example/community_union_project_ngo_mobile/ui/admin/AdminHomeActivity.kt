package com.example.community_union_project_ngo_mobile.ui.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityAdminHomeBinding
import com.example.community_union_project_ngo_mobile.ui.agent.ListOfTicketsActivity

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnViewCurrentTickets.setOnClickListener {
            val intent = Intent(this, ListOfTicketsActivity::class.java)
            intent.putExtra("USER_ROLE", "ADMIN")
            startActivity(intent)
        }

        binding.btnPendingVerifications.setOnClickListener {
            val intent = Intent(this, PendingVerificationActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewSyncLogs.setOnClickListener {
            val intent = Intent(this, ViewSyncLogsActivity::class.java)
            startActivity(intent)
        }

        binding.btnTools.setOnClickListener {
            val intent = Intent(this, ToolsActivity::class.java)
            startActivity(intent)
        }
        
        binding.btnTerminationRequests.setOnClickListener {
            val intent = Intent(this, TerminationRequestsActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }
}