package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityPartnerNgoListBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.firestore.FirebaseFirestore

class PartnerNgoListActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityPartnerNgoListBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartnerNgoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").whereEqualTo("role", "ngo").whereEqualTo("isVerified", true).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val ngoName = document.getString("ngoName")
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
                            intent.putExtra("NGO_ID", document.id)
                            startActivity(intent)
                        }
                    }
                    binding.llNgoList.addView(button)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting documents: ${exception.message}", Toast.LENGTH_SHORT).show()
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