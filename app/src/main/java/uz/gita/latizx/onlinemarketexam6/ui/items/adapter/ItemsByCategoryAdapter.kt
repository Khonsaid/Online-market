package uz.gita.latizx.onlinemarketexam6.ui.items.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.ItemProductBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.utils.BitmapConverter
import uz.gita.latizx.onlinemarketexam6.utils.formatWithSeparator

class ItemsByCategoryAdapter : ListAdapter<ItemEntity, ItemsByCategoryAdapter.ItemsByCategoryVH>(ItemsByCategoryDiffUtil) {
    private var changeFavouriteStateListener: ((Int, ItemEntity) -> Unit)? = null
    private var clickItemListener: ((Int, ItemEntity) -> Unit)? = null
    private var clickByListener: ((Int, ItemEntity) -> Unit)? = null

    object ItemsByCategoryDiffUtil : DiffUtil.ItemCallback<ItemEntity>() {
        override fun areItemsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean = oldItem.favourite == newItem.favourite
    }

    inner class ItemsByCategoryVH(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener { clickItemListener?.invoke(adapterPosition, getItem(adapterPosition)) }
                btnLike.setOnClickListener { changeFavouriteStateListener?.invoke(adapterPosition, getItem(adapterPosition)) }
                btnBy.setOnClickListener { clickByListener?.invoke(adapterPosition, getItem(adapterPosition)) }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.apply {
                ivLike.setImageResource(if (getItem(adapterPosition).favourite == 0) R.drawable.ic_lice0 else R.drawable.ic_like1)
                productName.text = getItem(adapterPosition).name
                productCategory.text = getItem(adapterPosition).description
                if (getItem(adapterPosition).discount > 0) {
                    productPrice.text ="${getItem(adapterPosition).price.toInt().toString().formatWithSeparator()} so'm"
                    productPrice.paintFlags = productPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    productNewPrice.text = (getItem(adapterPosition).price - (getItem(adapterPosition).price * getItem(adapterPosition).discount) / 100).toInt().toString().formatWithSeparator()

                    cvAksiya.visibility = View.VISIBLE
                    when {
                        getItem(adapterPosition).discount > 60 -> {
                            cvAksiya.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#004CFF"))
                            tvAksiya.text = "Mega Aksiya"
                        }

                        getItem(adapterPosition).discount > 40 -> {
                            cvAksiya.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#7000FF"))
                            tvAksiya.text = "Eksklyuziv"
                        }

                        else -> {
                            cvAksiya.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3B007D"))
                            tvAksiya.text = "Aksiya"
                        }
                    }
                } else {
                    productPrice.visibility = View.INVISIBLE
                    productNewPrice.text = "${getItem(adapterPosition).price.toInt()}".formatWithSeparator()
                    cvAksiya.visibility = View.INVISIBLE
                }
                itemImg.setImageBitmap(BitmapConverter.byteArrayToBitmap(getItem(adapterPosition).image))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsByCategoryVH =
        ItemsByCategoryVH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ItemsByCategoryVH, position: Int) {
        holder.bind()
    }

    fun setChangeFavouriteStateListener(block: (Int, ItemEntity) -> Unit) {
        changeFavouriteStateListener = block
    }

    fun setClickItemListener(block: (Int, ItemEntity) -> Unit) {
        clickItemListener = block
    }

    fun setClickByListener(block: (Int, ItemEntity) -> Unit) {
        clickByListener = block
    }
}