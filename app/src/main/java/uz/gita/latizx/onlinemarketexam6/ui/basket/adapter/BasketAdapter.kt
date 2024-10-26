package uz.gita.latizx.onlinemarketexam6.ui.basket.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.latizx.onlinemarketexam6.databinding.ItemBasketBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity
import uz.gita.latizx.onlinemarketexam6.utils.BitmapConverter
import uz.gita.latizx.onlinemarketexam6.utils.formatWithSeparator

class BasketAdapter : ListAdapter<KarzinaEntity, BasketAdapter.BasketVH>(BasketDiffUtil) {
    private var onClickPlus: ((KarzinaEntity) -> Unit)? = null
    private var onClickMinus: ((KarzinaEntity) -> Unit)? = null
    private var onLongClickMinus: ((KarzinaEntity) -> Unit)? = null
    private var onClickX: ((KarzinaEntity) -> Unit)? = null

    object BasketDiffUtil : DiffUtil.ItemCallback<KarzinaEntity>() {
        override fun areItemsTheSame(oldItem: KarzinaEntity, newItem: KarzinaEntity): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: KarzinaEntity, newItem: KarzinaEntity): Boolean = oldItem.count == newItem.count
    }

    inner class BasketVH(private val binding: ItemBasketBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                btnPlus.setOnClickListener { onClickPlus?.invoke(getItem(adapterPosition)) }
                btnMinus.setOnClickListener { onClickMinus?.invoke(getItem(adapterPosition)) }
                btnX.setOnClickListener { onClickX?.invoke(getItem(adapterPosition)) }
                btnMinus.setOnLongClickListener { onLongClickMinus?.invoke(getItem(adapterPosition)); false }
            }
        }

        fun bind() {
            binding.apply {
                tvCount.text = getItem(adapterPosition).count.toString()
                productName.text = getItem(adapterPosition).name
                productDesc.text = getItem(adapterPosition).description

                if (getItem(adapterPosition).discount > 0) {
                    val priceOne =
                        (getItem(adapterPosition).price - ((getItem(adapterPosition).price * getItem(adapterPosition).discount) / 100)).toInt()
                    productPriceOne.text = "${priceOne.toString().formatWithSeparator()} so'm"
                    productOldPrice.text =
                        "${(getItem(adapterPosition).price * getItem(adapterPosition).count).toInt().toString().formatWithSeparator()} so'm"
                    productOldPrice.paintFlags = productPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    productPrice.text =
                        "${(priceOne * getItem(adapterPosition).count).toString().formatWithSeparator()} so'm"

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
                    productOldPrice.visibility = View.INVISIBLE
                    productPriceOne.text = "${getItem(adapterPosition).price.toInt().toString().formatWithSeparator()} so'm"
                    productPrice.text =
                        "${(getItem(adapterPosition).price * getItem(adapterPosition).count).toInt().toString().formatWithSeparator()} so'm"
                    cvAksiya.visibility = View.INVISIBLE
                }
                orderImg.setImageBitmap(BitmapConverter.byteArrayToBitmap(getItem(adapterPosition).image))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketVH =
        BasketVH(ItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BasketVH, position: Int) {
        holder.bind()
    }

    fun setOnClickPlus(listen: (KarzinaEntity) -> Unit) {
        onClickPlus = listen
    }

    fun setOnClickMinus(listen: (KarzinaEntity) -> Unit) {
        onClickMinus = listen
    }

    fun setLongOnClickMinus(listen: (KarzinaEntity) -> Unit) {
        onClickMinus = listen
    }

    fun setOnClickX(listen: (KarzinaEntity) -> Unit) {
        onClickX = listen
    }
}