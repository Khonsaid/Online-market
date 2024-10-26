package uz.gita.latizx.onlinemarketexam6.ui.items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        presenter = ItemsByCategoryPresenter(this)
        presenter.getCategoryId(args.categoryId)
        binding.rvProducts.adapter = adapter

        binding.apply {
            btnAdd.setOnClickListener { presenter.clickAdd() }
            btnBack.setOnClickListener { presenter.clickBack() }
        }

        adapter.apply {
            setClickItemListener { _, itemEntity ->
                presenter.clickItem(itemEntity)
            }
            setClickByListener { _, itemEntity ->
                presenter.clickBuy(itemEntity)
            }
            setChangeFavouriteStateListener { _, itemEntity ->
                presenter.clickLike(itemEntity)
            }
        }
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

    override fun openInfoScreen(itemEntity: ItemEntity) {
        findNavController().navigate(ItemsByCategoryScreenDirections.actionItemsByCategoryScreenToInfoScreen(itemEntity.id))
    }

    override fun showToast(itemEntity: ItemEntity) {
        Toast.makeText(requireContext(), "${itemEntity.name} karzinaga qo'shildi!", Toast.LENGTH_SHORT).show()
    }
}