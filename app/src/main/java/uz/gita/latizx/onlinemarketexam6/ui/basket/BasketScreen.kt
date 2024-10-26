package uz.gita.latizx.onlinemarketexam6.ui.basket

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenBasketBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity
import uz.gita.latizx.onlinemarketexam6.ui.basket.adapter.BasketAdapter
import uz.gita.latizx.onlinemarketexam6.utils.formatWithSeparator

class BasketScreen : Fragment(), BasketContract.View {
    private var _binding: ScreenBasketBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: BasketContract.Presenter
    private lateinit var adapter: BasketAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ScreenBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = BasketAdapter()
        presenter = BasketPresenter(this)

        adapter.apply {
            setOnClickX { presenter.clickRemove(it) }
            setOnClickPlus { presenter.clickPlus(it) }
            setOnClickMinus { presenter.clickMinus(it) }
            setLongOnClickMinus { presenter.clickLongMinus(it) }
        }

        binding.apply {
            rvBasket.adapter = adapter
            btnBuy.setOnClickListener { presenter.clickBuy() }
        }
    }

    override fun submitList(list: List<KarzinaEntity>) {
        adapter.submitList(list)
    }

    override fun showRemoveDialog(karzinaEntity: KarzinaEntity) {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_remove, null)
        view.apply {
            findViewById<TextView>(R.id.tv_success).text = "Keyin orqaga qayterib bo'lmaydi!"
            findViewById<TextView>(R.id.tv_done).text = "Tanlangan mahsulotni o'chirishni hohlaysizmi?"
            findViewById<TextView>(R.id.tv_success).textSize = 22f
        }
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        val dialog = builder.create()
        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setCancelable(false)
        dialog.show()

        view.findViewById<AppCompatButton>(R.id.btn_yes_delete).setOnClickListener {
            presenter.removeItem(karzinaEntity)
            dialog.dismiss()
        }
        view.findViewById<AppCompatButton>(R.id.btn_no_delete).setOnClickListener { dialog.dismiss() }
    }

    override fun showPlaceHolder(isVisible: Boolean) {
        binding.cvLogo.visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.text.visibility = if (isVisible) View.VISIBLE else View.GONE

        binding.nestedScrollView3.visibility = if (isVisible) View.GONE else View.VISIBLE
        binding.constraintLayout3.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    override fun loadData(list: List<KarzinaEntity>, totalSum: Int, totalSumWithDiscount: Int, countKorzina: Int) {
        binding.apply {
            tvCount.text = "$countKorzina ta mahsulot savatda"
//            tvCountTitle.text = "$countKorzina ta mahsulot"
            tvCountBottom.text = "$countKorzina ta mahsulot"

            tvPrice.text = "${totalSum.toString().formatWithSeparator()} so'm"
            tvDiscount.text = "-${(totalSum - totalSumWithDiscount).toString().formatWithSeparator()} so'm"
            tvTotal.text = "${totalSumWithDiscount.toString().formatWithSeparator()} so'm"
            tvTotalBottom.text = "${totalSumWithDiscount.toString().formatWithSeparator()} so'm"
        }
    }

    override fun showSuccessDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_success, null)
        view.findViewById<TextView>(R.id.tv_success).text = "Buyurtmangiz qabul qilibdi tez orada siz bilan aloqaga chiqishdi!"
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        val dialog = builder.create()
        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setCancelable(false)
        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
        }, 1500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
