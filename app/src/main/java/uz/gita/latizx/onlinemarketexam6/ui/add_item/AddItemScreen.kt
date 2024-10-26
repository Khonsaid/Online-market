package uz.gita.latizx.onlinemarketexam6.ui.add_item

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
            checkbox.setOnCheckedChangeListener { _, isChecked -> presenter.checkBoxListener(isChecked) }
            inputImg.setOnClickListener { presenter.clickSelectImg() }

            btnSave.setOnClickListener {
                presenter.clickSaveBtn(
                    name = inputName.text.toString().trim(),
                    description = inputDescriotion.text.toString().trim(),
                    price = inputPrice.text.toString().trim(),
                    discount = inputDiscount.text.toString().trim(),
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
            inputDiscountLayout.visibility = if (isVisibility) View.VISIBLE else View.GONE
            tvDiscount.visibility = if (isVisibility) View.VISIBLE else View.GONE
        }
    }

    override fun openPrevScreen() {
        findNavController().popBackStack()
    }

    override fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryActivityResultLauncher.launch(intent)
    }

    private val galleryActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val imgUri = data!!.data

                try {
                    // URI dan Bitmap olish
                    val bitmap = if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imgUri)
                    } else {
                        val source = ImageDecoder.createSource(requireContext().contentResolver, imgUri!!)
                        ImageDecoder.decodeBitmap(source)
                    }
                    binding.inputImg.setImageBitmap(bitmap)
                    presenter.setBitmap(bitmap)

                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Rasmni yuklashda xatolik: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
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