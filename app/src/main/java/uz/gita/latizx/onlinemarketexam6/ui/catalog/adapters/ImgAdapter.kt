package uz.gita.latizx.onlinemarketexam6.ui.catalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.ItemImgBinding

class ImgAdapter(private val list: List<Int>) : RecyclerView.Adapter<ImgAdapter.ImgVH>() {
    private var clickIngListener: ((Int) -> Unit)? = null
    private var lastIndex = 0

    inner class ImgVH(private val binding: ItemImgBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (lastIndex != adapterPosition) {
                    notifyItemChanged(lastIndex)
                    lastIndex = adapterPosition
                    clickIngListener?.invoke(list[adapterPosition])
                    binding.llCategory.setBackgroundResource(R.drawable.bg_line)
                }
            }
        }

        fun bind() {
            binding.apply {
                imageView.setImageResource(list[adapterPosition])

                if (adapterPosition == lastIndex) binding.llCategory.setBackgroundResource(R.drawable.bg_line)
                else binding.llCategory.setBackgroundResource(R.drawable.bg_trans)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgVH =
        ImgVH(ItemImgBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ImgVH, position: Int) {
        holder.bind()
    }

    fun setClickImgListener(block: (Int) -> Unit) {
        clickIngListener = block
    }
}