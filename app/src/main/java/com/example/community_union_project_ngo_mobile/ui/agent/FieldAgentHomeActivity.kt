package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentHomeBinding
import com.example.community_union_project_ngo_mobile.ui.user.GoFundMeActivity
import com.example.community_union_project_ngo_mobile.ui.user.PartnerNgoListActivity

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
            intent.putExtra("USER_ROLE", "AGENT")
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
            val intent = Intent(this, FieldAgentSettingsActivity::class.java)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }
}