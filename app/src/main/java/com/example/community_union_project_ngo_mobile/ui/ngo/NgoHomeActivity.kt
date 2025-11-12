package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoHomeBinding
import com.example.community_union_project_ngo_mobile.ui.agent.ListOfTicketsActivity
import com.example.community_union_project_ngo_mobile.ui.user.AgentInformationActivity
import com.example.community_union_project_ngo_mobile.ui.user.GoFundMeActivity
import com.example.community_union_project_ngo_mobile.ui.user.PartnerNgoListActivity

class NgoHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNgoHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ngoName = intent.getStringExtra("NGO_NAME")
        binding.tvWelcome.text = "Welcome\n${ngoName}"

        binding.btnCurrentTickets.setOnClickListener {
            val intent = Intent(this, ListOfTicketsActivity::class.java)
            intent.putExtra("USER_ROLE", "NGO")
            startActivity(intent)
        }

        binding.btnContactAnAgent.setOnClickListener {
            val intent = Intent(this, AgentInformationActivity::class.java)
            startActivity(intent)
        }

        binding.btnOfferingAid.setOnClickListener {
            val intent = Intent(this, NgoOfferingAidActivity::class.java)
            intent.putExtra("NGO_NAME", ngoName)
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
            val intent = Intent(this, NgoSettingsActivity::class.java)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }
}