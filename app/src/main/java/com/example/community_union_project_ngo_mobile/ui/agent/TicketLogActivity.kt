package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityTicketLogBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class TicketLogActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityTicketLogBinding

    private val ticketCategories = listOf(
        "Food",
        "Housing",
        "Education",
        "Health Care",
        "Employment",
        "Clothing",
        "Transport"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        ticketCategories.forEach { category ->
            val button = Button(this).apply {
                text = category
                setBackgroundResource(R.drawable.edit_text_background)
                setOnClickListener {
                    val intent = Intent(this@TicketLogActivity, LogTicketActivity::class.java)
                    intent.putExtra("TICKET_CATEGORY", category)
                    startActivity(intent)
                }
            }
            binding.llCategoryButtons.addView(button)
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