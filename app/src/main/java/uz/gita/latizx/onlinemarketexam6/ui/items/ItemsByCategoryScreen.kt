package uz.gita.latizx.onlinemarketexam6.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenItemsByCategoryBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.ui.items.adapter.ItemsByCategoryAdapter

class ItemsByCategoryScreen : Fragment(), ItemsByCategoryContract.View {
    private var _binding: ScreenItemsByCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: ItemsByCategoryContract.Presenter
    private lateinit var adapter: ItemsByCategoryAdapter
    private val args: ItemsByCategoryScreenArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ScreenItemsByCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ItemsByCategoryAdapter()
        binding.btnAdd.setOnClickListener { presenter.clickAdd() }
        presenter.getCategoryId(args.categoryId)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun lostData(list: List<ItemEntity>) {
        adapter.submitList(list)
    }

    override fun openAddScreen(categoryId: Long) {
        findNavController().navigate(ItemsByCategoryScreenDirections.actionItemsByCategoryScreenToAddItemScreen(args.categoryId))
    }

    override fun openPrevScreen() {
        findNavController().popBackStack()
    }
}