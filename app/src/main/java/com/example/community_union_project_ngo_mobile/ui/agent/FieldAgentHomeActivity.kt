package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentHomeBinding

class FieldAgentHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFieldAgentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val agentName = intent.getStringExtra("AGENT_NAME")
        binding.tvWelcome.text = "Welcome\n${agentName}"

        binding.btnRegisterBeneficiary.setOnClickListener {
            val intent = Intent(this, RegisterBeneficiaryActivity::class.java)
            startActivity(intent)
        }

        binding.btnBeneficiaryList.setOnClickListener {
            val intent = Intent(this, BeneficiaryListActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogTicket.setOnClickListener {
            val intent = Intent(this, TicketLogActivity::class.java)
            startActivity(intent)
        }

        binding.btnCurrentTickets.setOnClickListener {
            val intent = Intent(this, ListOfTicketsActivity::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, FieldAgentSettingsActivity::class.java)
            startActivity(intent)
        }
    }
}