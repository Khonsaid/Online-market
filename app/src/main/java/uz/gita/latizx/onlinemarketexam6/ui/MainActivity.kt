package uz.gita.latizx.onlinemarketexam6.ui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.btmNavView.setupWithNavController(navController)

        binding.btmNavView.apply {
            menu.forEach { removeBadge(it.itemId) }
        }

//        var badge =  binding.btmNavView.getOrCreateBadge(R.id.catalogScreen);
//        badge.isVisible = true;
//        badge.number = 99;

        binding.btmNavView.let { bottomNav ->
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.mainScreen, R.id.catalogScreen, R.id.profileScreen, R.id.basketScreen -> bottomNav.visibility = View.VISIBLE
                    else -> {
                        bottomNav.visibility = View.GONE
                    }
                }
            }
        }
        val fadeNavOptions = NavOptions.Builder()
            .setEnterAnim(android.R.anim.fade_in)
            .setExitAnim(android.R.anim.fade_out)
            .setPopEnterAnim(android.R.anim.fade_in)
            .setPopExitAnim(android.R.anim.fade_out)
            .build()

        binding.btmNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainScreen -> {
                    navController.navigate(R.id.mainScreen, null, fadeNavOptions) // yoki fadeNavOptions
                    true
                }
                R.id.catalogScreen -> {
                    navController.navigate(R.id.catalogScreen, null, fadeNavOptions)
                    true
                }
                R.id.profileScreen -> {
                    navController.navigate(R.id.profileScreen, null, fadeNavOptions)
                    true
                }
                R.id.basketScreen -> {
                    navController.navigate(R.id.basketScreen, null, fadeNavOptions)
                    true
                }
                else -> false
            }
        }
    }
}