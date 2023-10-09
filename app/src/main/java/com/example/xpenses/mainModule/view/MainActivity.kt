package com.example.xpenses.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.xpenses.R
import com.example.xpenses.common.utils.OptionsMenu
import com.example.xpenses.databinding.ActivityMainBinding
import com.example.xpenses.mainModule.viewModel.MainViewModel
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        setupViewModel()
        setupObservers()
    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.user.observe(this) { user ->
                if (user == null) {
                    Log.d("ANT", "")
                }
                it.snackbarMsg.observe(this) { resMsg ->
                    Snackbar.make(binding.root, resMsg, Snackbar.LENGTH_SHORT).show()
                }
                it.initialSetupEvent.observe(this) { userPreferences ->
                    setupNavigationDrawer(userPreferences.lastDestination)
                }
            }
        }
    }

    private fun setupViewModel() {
        /*val vm = ViewModelProvider(
            this,
            MainViewModelFactory(MainRepository())
        )[MainViewModel::class.java]
         */

        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)


    }

    private fun setupNavigationDrawer(lastDestination: String) {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        val graph = navController.navInflater.inflate(R.navigation.main_navigation)
        graph.setStartDestination(OptionsMenu.valueOf(lastDestination).menuId)
        navController.setGraph(graph, null)

        navController.addOnDestinationChangedListener { controller, _, _ ->
            val menuId = controller.currentDestination?.id
            val nameDestination = OptionsMenu.values().first { it.menuId == menuId }.name
            binding.viewModel?.updateLastDestination(nameDestination)
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_today, R.id.nav_record), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel?.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.viewModel?.pause()
    }
}