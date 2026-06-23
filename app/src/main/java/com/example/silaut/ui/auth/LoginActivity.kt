package com.example.silaut.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.silaut.MainActivity
import com.example.silaut.data.local.AppDatabase_018
import com.example.silaut.data.local.PreferenceManager_018
import com.example.silaut.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding_018: ActivityLoginBinding
    private val db_018 by lazy { AppDatabase_018.getDatabase_018(this) }
    private val pref_018 by lazy { PreferenceManager_018(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        if (pref_018.isLoggedIn_018()) {
            navigateToDashboard_018(pref_018.getUserRole_018())
        }

        binding_018 = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding_018.root)

        binding_018.btnLogin.setOnClickListener {
            val email_018 = binding_018.etEmail.text.toString()
            val password_018 = binding_018.etPassword.text.toString()

            if (email_018.isNotEmpty() && password_018.isNotEmpty()) {
                lifecycleScope.launch {
                    val user_018 = db_018.userDao_018().login_018(email_018, password_018)
                    if (user_018 != null) {
                        pref_018.setUserLogin_018(user_018.id_018, user_018.role_018)
                        Toast.makeText(this@LoginActivity, "Selamat datang, ${user_018.nama_018}!", Toast.LENGTH_SHORT).show()
                        navigateToDashboard_018(user_018.role_018)
                    } else {
                        Toast.makeText(this@LoginActivity, "Email atau Password salah", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Mohon isi semua bidang", Toast.LENGTH_SHORT).show()
            }
        }

        binding_018.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun navigateToDashboard_018(role: String?) {
        val intent = if (role == "ADMIN") {
            Intent(this, com.example.silaut.ui.admin.AdminMainActivity_018::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
