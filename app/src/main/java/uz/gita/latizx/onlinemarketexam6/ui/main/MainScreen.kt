package uz.gita.latizx.onlinemarketexam6.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenMainBinding

class MainScreen : Fragment(), MainContract.View {
    private var _binding: ScreenMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = MainPresenter(this)

    }

    override fun loadAdvertising(list: ArrayList<SlideModel>) {
        binding.imageSlider.apply {
            setImageList(list, ScaleTypes.FIT)
            setSlideAnimation(AnimationTypes.DEPTH_SLIDE)
            startSliding(3000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
