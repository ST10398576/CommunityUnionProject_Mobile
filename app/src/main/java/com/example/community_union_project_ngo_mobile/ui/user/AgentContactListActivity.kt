package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityAgentContactListBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class AgentContactListActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityAgentContactListBinding

    // Placeholder for agent data
    private val agents = listOf(
        "David Sunday",
        "Megan Cross",
        "Paul Ndlovu",
        "Florence Perreault",
        "Ashley Pierce",
        "Abigail Von Revista",
        "Vivian Scott"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        for (agentName in agents) {
            val button = Button(this).apply {
                text = "Contact $agentName"
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 16, 0, 0)
                }
                setBackgroundResource(R.drawable.button_background)
                setTextColor(resources.getColor(android.R.color.white))
                setOnClickListener {
                    val intent = Intent(this@AgentContactListActivity, AgentDetailActivity::class.java)
                    intent.putExtra("AGENT_NAME", agentName)
                    startActivity(intent)
                }
            }
            binding.llAgentList.addView(button)
        }
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}