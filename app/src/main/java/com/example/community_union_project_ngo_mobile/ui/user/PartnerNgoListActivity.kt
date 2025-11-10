package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityPartnerNgoListBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class PartnerNgoListActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityPartnerNgoListBinding

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
        binding = ActivityPartnerNgoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        for (ngoName in ngos) {
            val button = Button(this).apply {
                text = ngoName
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 16, 0, 0)
                }
                setBackgroundResource(R.drawable.button_background)
                setTextColor(resources.getColor(android.R.color.white))
                setOnClickListener {
                    val intent = Intent(this@PartnerNgoListActivity, NgoDetailActivity::class.java)
                    intent.putExtra("NGO_NAME", ngoName)
                    startActivity(intent)
                }
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
        // TODO: Implement observers
    }
}