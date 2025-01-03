package uz.gita.latizx.onlinemarketexam6.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenFavouriteBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.ui.items.adapter.ItemsByCategoryAdapter

class FavouriteScreen : Fragment(), FavouriteContract.View {
    private var _binding: ScreenFavouriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: FavouritePresenter
    private lateinit var adapter: ItemsByCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ItemsByCategoryAdapter()
        presenter = FavouritePresenter(this)
        binding.rvProducts.adapter = adapter
        binding.btnBack.setOnClickListener { presenter.clickBack() }

        adapter.apply {
            setClickItemListener { _, itemEntity ->
                presenter.clickItem(itemEntity.id)
            }
            setClickByListener { _, itemEntity ->
                presenter.clickBuy(itemEntity)
            }
            setChangeFavouriteStateListener { _, itemEntity ->
                presenter.clickLike(itemEntity)
            }
        }
    }

    override fun lostData(list: List<ItemEntity>) {
        adapter.submitList(list)

        binding.apply {
            cvLogo.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
            text.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun showToast(itemEntity: ItemEntity) {
        Toast.makeText(requireContext(), "${itemEntity.name} karzinaga qo'shildi!", Toast.LENGTH_SHORT).show()
    }

    override fun openInfoScreen(itemId: Long) {
        findNavController().navigate(FavouriteScreenDirections.actionFavouriteScreenToInfoScreen(itemId))
    }

    override fun openPrevScreen() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}