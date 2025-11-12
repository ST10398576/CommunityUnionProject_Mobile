package com.example.community_union_project_ngo_mobile.ui.ngo

import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoOfferingAidBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class NgoOfferingAidActivity : BaseAuthActivity() {

    private lateinit var binding: ActivityNgoOfferingAidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoOfferingAidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val ngoName = intent.getStringExtra("NGO_NAME")
        binding.etNgoName.setText(ngoName)
    }

    override fun setupListeners() {
        binding.btnConfirm.setOnClickListener {
            // TODO: Save the NGO's information to the database
            finish()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers if needed
    }
}
