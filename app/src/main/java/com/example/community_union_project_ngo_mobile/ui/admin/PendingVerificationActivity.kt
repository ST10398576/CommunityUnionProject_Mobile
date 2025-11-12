package com.example.community_union_project_ngo_mobile.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityPendingVerificationBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.firestore.FirebaseFirestore

data class VerificationItem(val id: String, val type: String, val name: String, val details: String)

class PendingVerificationActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityPendingVerificationBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendingVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").whereEqualTo("isVerified", false).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val role = document.getString("role")
                    val id = document.id
                    val name = if (role == "ngo") document.getString("ngoName") else document.getString("fullName")
                    val details = if (role == "ngo") {
                        "Registration Number: ${document.getString("registrationNumber")}\nPBO Number: ${document.getString("pboNumber")}\nStatus: Pending"
                    } else {
                        "Contact Number: ${document.getString("contact")}\nAssociated NGO: ${document.getString("associatedNgo")}\nLocation: ${document.getString("location")}\nStatus: Pending"
                    }
                    val item = VerificationItem(id, role ?: "", name ?: "", details)
                    val itemView = createVerificationItemView(item)
                    binding.llVerificationList.addView(itemView)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting documents: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun createVerificationItemView(item: VerificationItem): LinearLayout {
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 16, 0, 0)
            }
            setBackgroundResource(R.drawable.edit_text_background)
            setPadding(16, 16, 16, 16)
            setOnClickListener {
                val intent = if (item.type == "ngo") {
                    Intent(this@PendingVerificationActivity, VerifyNgoActivity::class.java)
                } else {
                    Intent(this@PendingVerificationActivity, VerifyFieldAgentActivity::class.java)
                }
                intent.putExtra("USER_ID", item.id)
                startActivity(intent)
            }
        }

        val typeTextView = TextView(this).apply {
            text = item.type.capitalize()
            textSize = 18f
            setTextColor(resources.getColor(android.R.color.black))
        }

        val detailsTextView = TextView(this).apply {
            text = "Name: ${item.name}\n${item.details}"
            setTextColor(resources.getColor(android.R.color.black))
        }

        linearLayout.addView(typeTextView)
        linearLayout.addView(detailsTextView)

        return linearLayout
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