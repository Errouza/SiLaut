package com.example.silaut.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.silaut.data.model.Chat_018
import com.example.silaut.databinding.ItemChatAdminBinding
import com.example.silaut.databinding.ItemChatUserBinding
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter(
    private val chats_018: List<Chat_018>,
    private val isViewerAdmin_018: Boolean // Flag untuk tahu siapa yang sedang melihat chat
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ME_018 = 1    // Pesan dari diri sendiri (Kanan/Biru)
    private val VIEW_TYPE_THEM_018 = 2  // Pesan dari lawan bicara (Kiri/Abu-abu)

    override fun getItemViewType(position: Int): Int {
        val chat_018 = chats_018[position]
        // Jika saya Admin dan pesan dari Admin -> ME
        // Jika saya User dan pesan BUKAN dari Admin -> ME
        return if (isViewerAdmin_018 == chat_018.isFromAdmin_018) {
            VIEW_TYPE_ME_018
        } else {
            VIEW_TYPE_THEM_018
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ME_018) {
            val binding_018 = ItemChatUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            UserViewHolder_018(binding_018)
        } else {
            val binding_018 = ItemChatAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdminViewHolder_018(binding_018)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat_018 = chats_018[position]
        val time_018 = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(chat_018.timestamp_018))
        
        if (holder is UserViewHolder_018) {
            holder.binding_018.tvMessageUser.text = chat_018.pesan_018
            holder.binding_018.tvTimeUser.text = time_018
        } else if (holder is AdminViewHolder_018) {
            holder.binding_018.tvMessageAdmin.text = chat_018.pesan_018
            holder.binding_018.tvTimeAdmin.text = time_018
        }
    }

    override fun getItemCount(): Int = chats_018.size

    class UserViewHolder_018(val binding_018: ItemChatUserBinding) : RecyclerView.ViewHolder(binding_018.root)
    class AdminViewHolder_018(val binding_018: ItemChatAdminBinding) : RecyclerView.ViewHolder(binding_018.root)
}
