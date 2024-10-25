package uz.gita.latizx.onlinemarketexam6.ui.items.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.latizx.onlinemarketexam6.databinding.ItemProductBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity

class ItemsByCategoryAdapter : ListAdapter<ItemEntity, ItemsByCategoryAdapter.ItemsByCategoryVH>(ItemsByCategoryDiffUtil) {
    private var changeFavouriteStateListener :((Int, ItemEntity) -> Unit)?=null

    object ItemsByCategoryDiffUtil : DiffUtil.ItemCallback<ItemEntity>() {
        override fun areItemsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean = oldItem == newItem
    }

    inner class ItemsByCategoryVH(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsByCategoryVH =
        ItemsByCategoryVH(ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ItemsByCategoryVH, position: Int) {
        holder.bind()
    }
    fun setChangeFavouriteStateListener(block: (Int, ItemEntity) -> Unit) {
        changeFavouriteStateListener = block
    }
}