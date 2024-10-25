package uz.gita.latizx.onlinemarketexam6.ui.catalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.latizx.onlinemarketexam6.databinding.ItemCategoryBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.CategoryEntity

class CategoryAdapter(private val list: ArrayList<CategoryEntity>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    private var onClickCategoryListener: ((CategoryEntity) -> Unit)? = null

    fun setOnClickCategoryListener(listener: (CategoryEntity) -> Unit) {
        onClickCategoryListener = listener
    }

    inner class CategoryVH(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onClickCategoryListener?.invoke(list[adapterPosition]) }
        }

        fun bind(data: CategoryEntity) {
            binding.apply {
                tvName.text = data.name
                ivCategory.setImageResource(data.img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH =
        CategoryVH(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind(list[position])
    }
}