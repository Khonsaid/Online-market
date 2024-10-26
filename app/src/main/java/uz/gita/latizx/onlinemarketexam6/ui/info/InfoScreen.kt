package uz.gita.latizx.onlinemarketexam6.ui.info

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenInfoBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.utils.BitmapConverter
import uz.gita.latizx.onlinemarketexam6.utils.formatWithSeparator

class InfoScreen : Fragment(), InfoContract.View {
    private var _binding: ScreenInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: InfoPresenter
    private val args: InfoScreenArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ScreenInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = InfoPresenter(this, args.itemId)

        binding.apply {
            btnBackInfo.setOnClickListener { presenter.clickBack() }
            btnBuy.setOnClickListener { presenter.clickAddCart() }
            icShop.setOnClickListener { presenter.clickGoToCart() }
            icBasket.setOnClickListener { presenter.updateItem() }
        }
    }

    override fun openPrevScreen() {
        findNavController().popBackStack()
    }

    override fun openCartScreen() {
        findNavController().navigate(InfoScreenDirections.actionInfoScreenToBasketScreen())
    }

    override fun loadData(itemData: ItemEntity) {
        binding.apply {
            icBasket.setImageResource(if (itemData.favourite == 0) R.drawable.ic_lice0 else R.drawable.ic_like1)
            productName.text = itemData.name
            productDesc.text = itemData.description
            itemImgProduct.setImageBitmap(BitmapConverter.byteArrayToBitmap(itemData.image))

            if (itemData.discount > 0) {
                val priceOne = (itemData.price - ((itemData.price * itemData.discount) / 100)).toInt()
                productOldPrice.text = "${itemData.price.toInt().toString().formatWithSeparator()} so'm"
                productOldPrice.paintFlags = productPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                productPrice.text = "${priceOne.toString().formatWithSeparator()} so'm"

                tvCountBottom.text = "${priceOne.toString().formatWithSeparator()} so'm"
                tvTotalBottom.text = "${itemData.price.toInt().toString().formatWithSeparator()} so'm"
                tvTotalBottom.paintFlags = productPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                cvAksiya.visibility = View.VISIBLE
                tvFoiz.visibility = View.VISIBLE

                when {
                    itemData.discount > 60 -> {
                        cvAksiya.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#004CFF"))
                        tvAksiya.text = "Mega Aksiya"
                        cvFoiz.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F4488D"))
                        tvFoiz.text = "-${itemData.discount}%"
                    }

                    itemData.discount > 40 -> {
                        cvAksiya.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#7000FF"))
                        tvAksiya.text = "Eksklyuziv"
                        cvFoiz.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#004CFF"))
                        tvFoiz.text = "-${itemData.discount}%"
                    }

                    else -> {
                        cvAksiya.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3B007D"))
                        tvAksiya.text = "Aksiya"
                        cvFoiz.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#A0ABFF"))
                        tvFoiz.text = "-${itemData.discount}%"
                    }
                }
            } else {
                productOldPrice.visibility = View.INVISIBLE
                productPrice.text = "${itemData.price.toInt().toString().formatWithSeparator()} so'm"
                tvCountBottom.text = "${itemData.price.toString().formatWithSeparator()} so'm"
                cvAksiya.visibility = View.INVISIBLE
                tvFoiz.visibility = View.INVISIBLE
            }
        }
    }

    override fun showToast(itemEntity: ItemEntity) {
        Toast.makeText(requireContext(), "${itemEntity.name} karzinaga qo'shildi!", Toast.LENGTH_SHORT).show()
    }

    override fun changeLike(like: Int) {
        Log.d("TTT", "changeLike: $like")
        binding.icBasket.setImageResource(if (like == 0) R.drawable.ic_lice0 else R.drawable.ic_like1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}