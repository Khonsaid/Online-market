package uz.gita.latizx.onlinemarketexam6.ui.profil.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.latizx.onlinemarketexam6.data.ProfileData
import uz.gita.latizx.onlinemarketexam6.databinding.ItemProfileBinding

class ProfileAdapter(private val list: List<ProfileData>) : RecyclerView.Adapter<ProfileAdapter.ProfileViewModel>() {
    private var itemProfileClickListener: ((ProfileData) -> Unit)? = null
    fun setItemProfileClickListener(block: (ProfileData) -> Unit) {
        itemProfileClickListener = block
    }

    inner class ProfileViewModel(private val binding: ItemProfileBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { itemProfileClickListener?.invoke(list[adapterPosition]) }
        }

        fun bind(data: ProfileData) {
            binding.apply {
                ivProfile.setImageResource(data.img)
                tvName.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewModel =
        ProfileViewModel(ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProfileViewModel, position: Int) {
        holder.bind(list[position])
    }
}