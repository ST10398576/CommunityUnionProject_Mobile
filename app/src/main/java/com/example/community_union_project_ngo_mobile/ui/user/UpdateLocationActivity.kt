package com.example.community_union_project_ngo_mobile.ui.user

import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityUpdateLocationBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UpdateLocationActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityUpdateLocationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = auth.currentUser!!.uid

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val location = document.getString("location")
                    // Assuming location is stored as "Area, City, Province"
                    val locationParts = location?.split(", ")
                    if (locationParts?.size == 3) {
                        binding.etOldLocationArea.setText(locationParts[0])
                        binding.etOldLocationCity.setText(locationParts[1])
                        binding.etOldLocationProvince.setText(locationParts[2])
                    }
                }
            }
    }

    override fun setupListeners() {
        binding.btnConfirm.setOnClickListener {
            val newArea = binding.etNewLocationArea.text.toString().trim()
            val newCity = binding.etNewLocationCity.text.toString().trim()
            val newProvince = binding.etNewLocationProvince.text.toString().trim()

            if (newArea.isNotEmpty() && newCity.isNotEmpty() && newProvince.isNotEmpty()) {
                val newLocation = "$newArea, $newCity, $newProvince"
                db.collection("users").document(userId).update("location", newLocation)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Location updated successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error updating location: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}