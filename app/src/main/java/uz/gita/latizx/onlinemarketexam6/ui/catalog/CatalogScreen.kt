package uz.gita.latizx.onlinemarketexam6.ui.catalog

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
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenCatalogBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.CategoryEntity
import uz.gita.latizx.onlinemarketexam6.ui.catalog.adapters.CategoryAdapter
import uz.gita.latizx.onlinemarketexam6.ui.catalog.dalog.AddDialogCategory

class CatalogScreen : Fragment(), CategoryContract.View {
    private var _binding: ScreenCatalogBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: CategoryContract.Presenter
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = CategoryPresenter(this)

        adapter.setOnClickCategoryListener {
            presenter.clickItem(it)
        }
        binding.btnAdd.setOnClickListener { presenter.clickAddBtn() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun loadData(list: ArrayList<CategoryEntity>) {
        binding.cvLogo.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        adapter = CategoryAdapter(list)
        binding.rvCatalog.adapter = adapter
    }

    override fun openItemByCategory(categoryEntity: CategoryEntity) {
        findNavController().navigate(CatalogScreenDirections.actionCatalogScreenToItemsByCategoryScreen(categoryEntity.id))
    }

    override fun notifyItemChanged(position: Int) {
        adapter.notifyItemChanged(position)
    }

    override fun showDialogAdd(list: List<Int>) {
        val dialog = AddDialogCategory(list).apply {
            setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialogTheme)
        }

        dialog.setOnClickSaveListener {
            presenter.addCategory(it)
        }
        dialog.show(parentFragmentManager, "AddDialogCategory")
    }

    override fun showSuccessDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_success, null)
        view.findViewById<TextView>(R.id.tv_success).text = "Category qo'shildi"
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
}