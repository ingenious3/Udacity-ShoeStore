package com.udacity.shoestore.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udacity.shoestore.R
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.models.ShoeStoreViewModel

class MainActivity : AppCompatActivity() {

    private val TAG : String? = MainActivity::class.simpleName
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var shoeStoreViewModel: ShoeStoreViewModel
    private lateinit var host: NavHostFragment
    private var menuItemLogout: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize the data-binding
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        host = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController

        // bind toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loginFragment,
                R.id.welcomeFragment
            )
        )
        // Set up Action Bar with appbar config and nav controller
        setupActionBar(navController, appBarConfiguration)

        // initialize viewmodel
        shoeStoreViewModel = ViewModelProvider(this).get(ShoeStoreViewModel::class.java)
        //observing destination and navigating to given direction
        shoeStoreViewModel.getDestinationId().observe(this, Observer {
            it?.let {
                Log.d(TAG, it.toString())
                navigate(it)
            }
        })
    }

    //navigate to given destination based on destinationId
    private fun navigate(destId: Int) {
        navController.navigate(destId)
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        // This allows to decide what label to show in the action bar
        // also it determines whether to show the up arrow or not
        setupActionBarWithNavController(navController, appBarConfig)

        // it determines whether to show Logout or not
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.label == "Login") {
                menuItemLogout?.isVisible = false
            } else {
                menuItemLogout?.isVisible = true
            }
        }

    }

    //attach navigation up to nav controller
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

    // setup options-menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)

        menuItemLogout = menu!!.getItem(0)
        menuItemLogout?.isVisible = false

        return true
    }

    // setup options-menu click event
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        shoeStoreViewModel.clearShoe()
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

}
