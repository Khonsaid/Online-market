package uz.gita.latizx.onlinemarketexam6.ui.login

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenLoginBinding

class LoginScreen : Fragment(), LoginContract.View {
    private var _binding: ScreenLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: LoginContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = LoginPresenter(this)

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
        }
    }

    override fun showSuccessDialogAndOpenHomeScreen() {
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
            findNavController().navigate(LoginScreenDirections.actionLoginScreenToMainScreen())
            dialog.dismiss()
        }, 1500)
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
