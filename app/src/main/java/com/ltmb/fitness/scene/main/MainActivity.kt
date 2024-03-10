package com.ltmb.fitness.scene.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseActivity
import com.ltmb.fitness.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    val navController: NavController by lazy { (supportFragmentManager.findFragmentById(R.id.main_host_fragment) as NavHostFragment).navController }

    override fun initialize() {
        super.initialize()

        setupBottomNavigationView()
        listenDestinationChanges()
    }

    private fun setupBottomNavigationView() {
        binding.mainBottomNav.setupWithNavController(navController)
    }

    private fun listenDestinationChanges() {
        navController.addOnDestinationChangedListener { _, _, args ->
            val visibility = if (shouldShowBottomNav(args)) View.VISIBLE else View.GONE

            if (binding.mainBottomNav.visibility != visibility) {
                binding.mainBottomNav.visibility = visibility
            }
        }
    }

    private fun shouldShowBottomNav(args: Bundle?): Boolean {
        return args?.getBoolean(getString(R.string.arg_show_bottom_nav)) == true
    }
}