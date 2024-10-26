package uz.gita.latizx.onlinemarketexam6.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenMainBinding
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.ui.items.adapter.ItemsByCategoryAdapter

class MainScreen : Fragment(), MainContract.View {
    private var _binding: ScreenMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: ItemsByCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ItemsByCategoryAdapter()
        presenter = MainPresenter(this)
        binding.rvMain.adapter = adapter
        binding.btnFavourite.setOnClickListener { presenter.clickFavouriteBtn() }

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

        binding.searchView.setOnQueryTextListener((object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.filterItems(newText)
                return true
            }
        }))
    }

    override fun loadAdvertising(list: ArrayList<SlideModel>) {
        binding.imageSlider.apply {
            setImageList(list, ScaleTypes.FIT)
            setSlideAnimation(AnimationTypes.DEPTH_SLIDE)
            startSliding(3000)
        }
    }

    override fun loadItems(list: List<ItemEntity>) {
        adapter.submitList(list)
    }

    override fun showToast(itemEntity: ItemEntity) {
        Toast.makeText(requireContext(), "${itemEntity.name} karzinaga qo'shildi!", Toast.LENGTH_SHORT).show()
    }

    override fun openNextScreen() {
        findNavController().navigate(MainScreenDirections.actionMainScreenToFavouriteScreen())
    }

    override fun showPlaceHolder(isVisible: Boolean) {
        binding.placeHolder.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun showImageSlider(isVisible: Boolean) {
        binding.imageSlider.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    override fun openInfoScreen(itemId: Long) {
        findNavController().navigate(MainScreenDirections.actionMainScreenToInfoScreen(itemId))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
