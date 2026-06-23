package com.example.silaut.ui.admin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silaut.data.local.AppDatabase_018
import com.example.silaut.data.model.User_018
import com.example.silaut.databinding.ActivityAdminChatListBinding
import com.example.silaut.databinding.ItemAdminChatUserBinding
import com.example.silaut.ui.chat.ChatActivity
import kotlinx.coroutines.launch

class AdminChatListActivity_018 : AppCompatActivity() {

    private lateinit var binding_018: ActivityAdminChatListBinding
    private val db_018 by lazy { AppDatabase_018.getDatabase_018(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_018 = ActivityAdminChatListBinding.inflate(layoutInflater)
        setContentView(binding_018.root)

        setupToolbar_018()
        setupRecyclerView_018()
    }

    private fun setupToolbar_018() {
        binding_018.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupRecyclerView_018() {
        binding_018.rvAdminChatUsers.layoutManager = LinearLayoutManager(this)
        
        lifecycleScope.launch {
            db_018.chatDao_018().getUsersWithChats_018().collect { userIds_018 ->
                if (userIds_018.isNotEmpty()) {
                    val users_018 = db_018.userDao_018().getUsersByIds_018(userIds_018)
                    binding_018.rvAdminChatUsers.adapter = UserListAdapter_018(users_018) { user_018 ->
                        val intent_018 = Intent(this@AdminChatListActivity_018, ChatActivity::class.java)
                        intent_018.putExtra("TARGET_USER_ID_018", user_018.id_018)
                        intent_018.putExtra("TARGET_USER_NAME_018", user_018.nama_018)
                        startActivity(intent_018)
                    }
                }
            }
        }
    }

    class UserListAdapter_018(
        private val users_018: List<User_018>,
        private val onClick_018: (User_018) -> Unit
    ) : RecyclerView.Adapter<UserListAdapter_018.ViewHolder>() {

        class ViewHolder(val itemBinding_018: ItemAdminChatUserBinding) : RecyclerView.ViewHolder(itemBinding_018.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding_018 = ItemAdminChatUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding_018)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user_018 = users_018[position]
            holder.itemBinding_018.tvUserName.text = user_018.nama_018
            holder.itemBinding_018.tvUserEmail.text = user_018.email_018
            holder.itemView.setOnClickListener { onClick_018(user_018) }
        }

        override fun getItemCount(): Int = users_018.size
    }
}
