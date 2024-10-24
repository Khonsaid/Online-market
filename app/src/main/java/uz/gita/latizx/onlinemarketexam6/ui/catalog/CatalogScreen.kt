package uz.gita.latizx.onlinemarketexam6.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenCatalogBinding
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenMainBinding

class CatalogScreen : Fragment() {
    private var _binding: ScreenCatalogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}