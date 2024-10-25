package uz.gita.latizx.onlinemarketexam6.ui.add_item

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
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenAddItemBinding

class AddItemScreen : Fragment(), AddItemContract.View {
    private var _binding: ScreenAddItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: AddItemPresenter
    private val args: AddItemScreenArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ScreenAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = AddItemPresenter(this)

        binding.apply {
            inputDiscount.addTextChangedListener { presenter.setDisCountNameListener(it.toString().trim()) }
            inputName.addTextChangedListener { presenter.setNameListener(it.toString().trim()) }
            inputPrice.addTextChangedListener { presenter.setPriceListener(it.toString().trim()) }
            inputDescriotion.addTextChangedListener { presenter.setDescriptionListener(it.toString().trim()) }

            btnBack.setOnClickListener { presenter.clickBack() }
            checkbox.setOnClickListener {
                presenter.checkBoxListener(it.isSelected)
            }

            btnSave.setOnClickListener {
                presenter.clickSaveBtn(
                    name = inputName.text.toString().trim(),
                    description = inputDescriotion.toString().trim(),
                    price = inputPrice.toString().trim(),
                    discount = inputDiscount.toString().trim(),
                    categoryId = args.categoryId
                )
            }
        }
    }

    override fun showSuccessDialogAndOpenPrevScreen() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_success, null)
        view.findViewById<TextView>(R.id.tv_success).text = "Product qo'shildi!"
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
            findNavController().popBackStack()
            dialog.dismiss()
        }, 1500)
    }

    override fun showDiscount(isVisibility: Boolean) {
        binding.apply {
            inputDiscount.visibility = if (isVisibility) View.VISIBLE else View.GONE
            tvDiscount.visibility = if (isVisibility) View.VISIBLE else View.GONE
        }
    }

    override fun openPrevScreen() {
        findNavController().popBackStack()
    }

    override fun openGallery() {

    }

    override fun enableBtn(isEnable: Boolean) {
        binding.btnSave.isEnabled = isEnable
    }

    override fun errorName(error: String) {
        binding.inputNameLayout.error = error
    }

    override fun errorDescription(error: String) {
        binding.inputDescriotionLayout.error = error
    }

    override fun errorPrice(error: String) {
        binding.inputPriceLayout.error = error
    }

    override fun errorDisCountName(error: String) {
        binding.inputDiscountLayout.error = error
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}