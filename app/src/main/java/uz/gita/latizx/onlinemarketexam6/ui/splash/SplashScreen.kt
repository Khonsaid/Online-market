package uz.gita.latizx.onlinemarketexam6.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.latizx.onlinemarketexam6.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
        }, 3000)
    }
}