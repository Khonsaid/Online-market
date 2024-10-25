package uz.gita.latizx.onlinemarketexam6.ui.catalog.dalog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.DialogAddCategoryBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.CategoryEntity
import uz.gita.latizx.onlinemarketexam6.ui.catalog.adapters.ImgAdapter

class AddDialogCategory(private val list: List<Int>) : DialogFragment() {
    private var _binding: DialogAddCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ImgAdapter
    private var text = ""
    private var idImg = R.drawable.ic_logo_category
    private var onClickSaveListener: ((CategoryEntity) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogAddCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ImgAdapter(list)
        binding.rvImg.adapter = adapter

        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        adapter.setClickImgListener { idImg = it }

        binding.apply {
            inputName.addTextChangedListener {
                text = it.toString().trim()
                if (text.length < 2) inputNameLayoutAdd.error = "Must be at least 2 letters!"
                else inputNameLayoutAdd.error = ""
                enableBtn(text)
            }

            btnAddCategory.setOnClickListener {
                onClickSaveListener?.invoke(CategoryEntity(id = 0, name = text, img = idImg))
                dialog?.dismiss()
            }
            btnClose.setOnClickListener { dialog?.dismiss() }
        }
    }

    fun setOnClickSaveListener(block: (CategoryEntity) -> Unit) {
        onClickSaveListener = block
    }

    private fun enableBtn(text: String) {
        binding.btnAddCategory.isEnabled = !(text.isEmpty() && text.isBlank())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}