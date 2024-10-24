package uz.gita.latizx.onlinemarketexam6.ui.personal

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenPersonalInfoBinding
import uz.gita.latizx.onlinemarketexam6.models.UserModel

class PersonalInfoScreen : Fragment(), PersonalInfoContract.View {
    private var _binding: ScreenPersonalInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: PersonalInfoContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenPersonalInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = PersonalInfoPresenter(this)

        binding.btnSave.setOnClickListener {
            presenter.clickSaveBtn(
                fullName = binding.inputFullName.text.toString().trim(),
                age = binding.inputAge.text.toString(),
                phone = binding.inputPhone.text.toString().trim(),
                nickName = binding.inputNickName.text.toString().trim(),
                password = binding.inputPassword.text.toString().trim(),
            )
        }
        binding.apply {
            inputFullName.addTextChangedListener { presenter.setFullNameListener(it.toString().trim()) }
            inputAge.addTextChangedListener { presenter.setAgeListener(it.toString()) }
            inputPhone.addTextChangedListener { presenter.setPhoneListener(it.toString().trim()) }
            inputNickName.addTextChangedListener { presenter.setNickNameListener(it.toString().trim()) }
            inputPassword.addTextChangedListener { presenter.setPasswordListener(it.toString().trim()) }

            btnDeleteUser.setOnClickListener { presenter.clickDeleteUser() }
            btnBack.setOnClickListener { presenter.clickBack() }
        }
    }

    override fun loadData(data: UserModel) {
        binding.apply {
            inputFullName.setText(data.fullName)
            inputAge.setText(data.age.toString())
            inputPhone.setText(data.phoneNumber)
            inputNickName.setText(data.nickName)
            inputPassword.setText(data.password)
        }
    }

    override fun showSuccessDialogAndOpenPrevScreen() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_success, null)

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

    override fun showDeleteDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_remove, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        val dialog = builder.create()
        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setCancelable(false)
        dialog.show()

        view.findViewById<AppCompatButton>(R.id.btn_yes_delete).setOnClickListener { presenter.deleteUser() }
        view.findViewById<AppCompatButton>(R.id.btn_no_delete).setOnClickListener { dialog.dismiss() }
    }

    override fun openPrevScreen() {
        findNavController().navigateUp()
    }

    override fun openLoginScreen() {
        val navOption = NavOptions.Builder().setPopUpTo(R.id.loginScreen, true).build()
        findNavController().navigate(PersonalInfoScreenDirections.actionPersonalInfoToLoginScreen(), navOption)
    }

    override fun enableBtn(isEnable: Boolean) {
        binding.btnSave.isEnabled = isEnable
    }

    override fun errorFullName(error: String) {
        binding.inputFullNameLayout.error = error
    }

    override fun errorAge(error: String) {
        binding.inputAgeLayout.error = error
    }

    override fun errorPhone(error: String) {
        binding.inputPhoneLayout.error = error
    }

    override fun errorNickName(error: String) {
        binding.inputNickLayout.error = error
    }

    override fun errorPassword(error: String) {
        binding.inputPasswordLayout.error = error
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
