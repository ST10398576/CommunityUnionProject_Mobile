package com.example.community_union_project_ngo_mobile.ui.agent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentRegisterBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FieldAgentRegisterActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFieldAgentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    private var employmentLetterUri: Uri? = null
    private var agentPhotoUri: Uri? = null

    companion object {
        private const val PICK_EMPLOYMENT_LETTER_REQUEST = 1
        private const val PICK_AGENT_PHOTO_REQUEST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val ngos = mutableListOf<String>()
        db.collection("users").whereEqualTo("role", "ngo").whereEqualTo("isVerified", true).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    ngos.add(document.getString("ngoName") ?: "")
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ngos)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spnAssociatedNgo.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting NGOs: ${exception.message}", Toast.LENGTH_SHORT).show()
            }

        val locations = arrayOf("Cape Town", "Stellenbosch", "Paarl")
        val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locations)
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnLocation.adapter = locationAdapter
    }

    override fun setupListeners() {
        binding.btnUploadEmploymentLetter.setOnClickListener {
            openFileChooser(PICK_EMPLOYMENT_LETTER_REQUEST)
        }

        binding.btnUploadAgentPhoto.setOnClickListener {
            openFileChooser(PICK_AGENT_PHOTO_REQUEST)
        }

        binding.btnRegister.setOnClickListener {
            val fullName = binding.etName.text.toString().trim()
            val contact = binding.etContact.text.toString().trim()
            val associatedNgo = binding.spnAssociatedNgo.selectedItem.toString()
            val ngoAgentId = binding.etNgoAgentId.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val location = binding.spnLocation.selectedItem.toString()

            if (fullName.isEmpty() || contact.isEmpty() || ngoAgentId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = "$ngoAgentId@cup.com"

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser!!.uid
                        uploadFiles(userId, fullName, email, contact, associatedNgo, ngoAgentId, location)
                    } else {
                        Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, FieldAgentLoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun openFileChooser(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = if (requestCode == PICK_EMPLOYMENT_LETTER_REQUEST) "application/pdf" else "image/*"
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            when (requestCode) {
                PICK_EMPLOYMENT_LETTER_REQUEST -> {
                    employmentLetterUri = data.data
                    binding.btnUploadEmploymentLetter.text = "Employment Letter Uploaded"
                }
                PICK_AGENT_PHOTO_REQUEST -> {
                    agentPhotoUri = data.data
                    binding.btnUploadAgentPhoto.text = "Agent Photo Uploaded"
                }
            }
        }
    }

    private fun uploadFiles(userId: String, fullName: String, email: String, contact: String, associatedNgo: String, ngoAgentId: String, location: String) {
        if (employmentLetterUri != null) {
            val employmentLetterRef = storage.reference.child("agent_documents/$userId/employment_letter.pdf")
            employmentLetterRef.putFile(employmentLetterUri!!)
                .addOnSuccessListener { 
                    val letterPath = employmentLetterRef.path
                    if (agentPhotoUri != null) {
                        val agentPhotoRef = storage.reference.child("agent_documents/$userId/agent_photo.jpg")
                        agentPhotoRef.putFile(agentPhotoUri!!)
                            .addOnSuccessListener { 
                                val photoPath = agentPhotoRef.path
                                saveUserToFirestore(userId, fullName, email, contact, associatedNgo, ngoAgentId, location, letterPath, photoPath)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Failed to upload agent photo: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        saveUserToFirestore(userId, fullName, email, contact, associatedNgo, ngoAgentId, location, letterPath, null)
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to upload employment letter: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            if (agentPhotoUri != null) {
                val agentPhotoRef = storage.reference.child("agent_documents/$userId/agent_photo.jpg")
                agentPhotoRef.putFile(agentPhotoUri!!)
                    .addOnSuccessListener { 
                        val photoPath = agentPhotoRef.path
                        saveUserToFirestore(userId, fullName, email, contact, associatedNgo, ngoAgentId, location, null, photoPath)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Failed to upload agent photo: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                saveUserToFirestore(userId, fullName, email, contact, associatedNgo, ngoAgentId, location, null, null)
            }
        }
    }

    private fun saveUserToFirestore(userId: String, fullName: String, email: String, contact: String, associatedNgo: String, ngoAgentId: String, location: String, employmentLetterPath: String?, agentPhotoPath: String?) {
        val user = hashMapOf(
            "fullName" to fullName,
            "email" to email,
            "contact" to contact,
            "associatedNgo" to associatedNgo,
            "ngoAgentId" to ngoAgentId,
            "location" to location,
            "employmentLetter" to (employmentLetterPath ?: ""),
            "agentPhoto" to (agentPhotoPath ?: ""),
            "role" to "agent",
            "isVerified" to true // Auto-verify
        )

        db.collection("users").document(userId)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, FieldAgentLoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error saving user details: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}