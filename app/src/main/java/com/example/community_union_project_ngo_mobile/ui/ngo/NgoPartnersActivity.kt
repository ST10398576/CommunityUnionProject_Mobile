package com.example.community_union_project_ngo_mobile.ui.ngo

import android.os.Bundle
import android.widget.Button
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoPartnersBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class NgoPartnersActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityNgoPartnersBinding

    // Placeholder for NGO data
    private val ngos = listOf(
        "Qhubeka Charity Foundation",
        "Food Forward SA",
        "The Lunch Box Foundation",
        "Food & Tress For Africa",
        "The Nelson Mandela Foundation",
        "Love Story Foundation",
        "Gift of the Givers"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoPartnersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        ngos.forEach { ngoName ->
            val button = Button(this).apply {
                text = ngoName
                setBackgroundResource(R.drawable.button_background)
                setTextColor(resources.getColor(android.R.color.white))
            }
            binding.llNgoList.addView(button)
        }
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}