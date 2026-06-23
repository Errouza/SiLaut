package com.example.silaut.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.silaut.data.local.AppDatabase_018
import com.example.silaut.data.model.User_018
import com.example.silaut.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding_018: ActivityRegisterBinding
    private val db_018 by lazy { AppDatabase_018.getDatabase_018(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_018 = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding_018.root)

        binding_018.btnRegister.setOnClickListener {
            val nama_018 = binding_018.etName.text.toString()
            val email_018 = binding_018.etEmail.text.toString()
            val password_018 = binding_018.etPassword.text.toString()

            if (nama_018.isNotEmpty() && email_018.isNotEmpty() && password_018.isNotEmpty()) {
                lifecycleScope.launch {
                    val existingUser_018 = db_018.userDao_018().getUserByEmail_018(email_018)
                    if (existingUser_018 == null) {
                        val role_018 = if (email_018.endsWith("@admin.silaut.com")) "ADMIN" else "USER"
                        
                        db_018.userDao_018().insertUser_018(User_018(nama_018 = nama_018, email_018 = email_018, password_018 = password_018, role_018 = role_018))
                        Toast.makeText(this@RegisterActivity, "Registrasi Berhasil! Silakan Masuk.", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Mohon isi semua bidang", Toast.LENGTH_SHORT).show()
            }
        }

        binding_018.tvLogin.setOnClickListener {
            finish()
        }
    }
}
