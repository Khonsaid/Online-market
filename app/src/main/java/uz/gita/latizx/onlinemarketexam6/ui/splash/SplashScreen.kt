package uz.gita.latizx.onlinemarketexam6.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.latizx.onlinemarketexam6.databinding.ScreenSplashBinding
import uz.gita.latizx.onlinemarketexam6.source.pref.MyPref

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {
    private var _binding: ScreenSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (MyPref.getInstance()
                    .checkUser() != null
            ) findNavController().navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
            else binding.btnStart.visibility = View.VISIBLE
        }, 300)

        binding.btnStart.setOnClickListener { findNavController().navigate(SplashScreenDirections.actionSplashScreenToLoginScreen()) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}