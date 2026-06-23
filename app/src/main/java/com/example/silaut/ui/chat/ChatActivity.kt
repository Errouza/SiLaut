package com.example.silaut.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silaut.data.local.AppDatabase_018
import com.example.silaut.data.local.PreferenceManager_018
import com.example.silaut.data.model.Chat_018
import com.example.silaut.databinding.ActivityChatBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity() {

    private lateinit var binding_018: ActivityChatBinding
    private val db_018 by lazy { AppDatabase_018.getDatabase_018(this) }
    private val pref_018 by lazy { PreferenceManager_018(this) }
    
    // Jika Admin yang buka, ambil ID user target dari Intent. Jika User, pakai ID sendiri.
    private val currentUserId_018 by lazy { pref_018.getUserId_018() }
    private val userRole_018 by lazy { pref_018.getUserRole_018() }
    private var targetUserId_018: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_018 = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding_018.root)

        targetUserId_018 = intent.getIntExtra("TARGET_USER_ID_018", currentUserId_018)
        val targetUserName_018 = intent.getStringExtra("TARGET_USER_NAME_018")
        
        if (userRole_018 == "ADMIN" && targetUserName_018 != null) {
            binding_018.toolbar.title = "Chat: $targetUserName_018"
        }

        setupToolbar_018()
        setupRecyclerView_018()
        setupListeners_018()
        observeChats_018()
    }

    private fun setupToolbar_018() {
        binding_018.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupRecyclerView_018() {
        binding_018.rvChat.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
    }

    private fun setupListeners_018() {
        binding_018.btnSend.setOnClickListener {
            val message_018 = binding_018.etMessage.text.toString().trim()
            if (message_018.isNotEmpty()) {
                sendMessage_018(message_018)
                binding_018.etMessage.text.clear()
            }
        }
    }

    private fun observeChats_018() {
        lifecycleScope.launch {
            db_018.chatDao_018().getChatHistory_018(targetUserId_018).collect { chatList_018 ->
                val isViewerAdmin_018 = userRole_018 == "ADMIN"
                binding_018.rvChat.adapter = ChatAdapter(chatList_018, isViewerAdmin_018)
                if (chatList_018.isNotEmpty()) {
                    binding_018.rvChat.scrollToPosition(chatList_018.size - 1)
                }
            }
        }
    }

    private fun sendMessage_018(text_018: String) {
        val isFromAdmin_018 = userRole_018 == "ADMIN"
        
        val chat_018 = Chat_018(
            userId_018 = targetUserId_018,
            pesan_018 = text_018,
            isFromAdmin_018 = isFromAdmin_018
        )

        lifecycleScope.launch {
            db_018.chatDao_018().insertChat_018(chat_018)
            
            // Hanya auto-reply jika pengirimnya adalah USER
            if (!isFromAdmin_018) {
                simulateAdminReply_018()
            }
        }
    }

    private suspend fun simulateAdminReply_018() {
        delay(2000) // Beri waktu lebih lama agar terasa nyata
        val adminChat_018 = Chat_018(
            userId_018 = targetUserId_018,
            pesan_018 = "Halo, pesan Anda telah kami terima. Admin akan segera membalas secara manual.",
            isFromAdmin_018 = true
        )
        db_018.chatDao_018().insertChat_018(adminChat_018)
    }
}
