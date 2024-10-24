package uz.gita.latizx.onlinemarketexam6.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.latizx.onlinemarketexam6.data.ProfileData
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenProfileBinding
import uz.gita.latizx.onlinemarketexam6.models.UserModel
import uz.gita.latizx.onlinemarketexam6.ui.profil.adapter.ProfileAdapter

class ProfileScreen : Fragment(), ProfileContract.View {
    private var _binding: ScreenProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: ProfileContract.Presenter
    private lateinit var adapter: ProfileAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = ProfilePresenter(this)

        adapter.setItemProfileClickListener {
            presenter.clickItem(it.name)
        }
        binding.btnLogOut.setOnClickListener { presenter.clickLogOut() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun openProfileInfoScreen() {
        findNavController().navigate(ProfileScreenDirections.actionProfileScreenToPersonalInfo())
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun loadData(list: List<ProfileData>) {
        adapter = ProfileAdapter(list)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun loadUserData(userModel: UserModel) {
        binding.apply {
            tvName.text = userModel.nickName
            tvPhone.text = userModel.phoneNumber
        }
    }
}