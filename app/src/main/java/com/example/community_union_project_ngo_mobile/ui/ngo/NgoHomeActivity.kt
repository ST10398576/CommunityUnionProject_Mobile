package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoHomeBinding

class NgoHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNgoHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve NGO name passed from login or previous screen
        val ngoName = intent.getStringExtra("NGO_NAME")
        binding.tvWelcome.text = "Welcome\n${ngoName ?: "NGO Partner"}"

        // Current Tickets
        binding.btnCurrentTickets.setOnClickListener {
            val intent = Intent(this, NgoCurrentTicketsActivity::class.java)
            startActivity(intent)
        }

        // Contact an Agent
        binding.btnContactAgent.setOnClickListener {
            val intent = Intent(this, NgoContactAgentActivity::class.java)
            startActivity(intent)
        }

        // Offering Aid
        binding.btnOfferingAid.setOnClickListener {
            val intent = Intent(this, NgoOfferingAidActivity::class.java)
            startActivity(intent)
        }

        // Partner NGO/NPOs
        binding.btnPartnerNgo.setOnClickListener {
            val intent = Intent(this, NgoPartnersActivity::class.java)
            startActivity(intent)
        }

        // Settings
        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, NgoSettingsActivity::class.java)
            startActivity(intent)
        }
    }
}
