package com.example.dz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.dz.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.mainToolbar
        setSupportActionBar(toolbar)

        val drawer = binding.drawerLayout
        val navigationView = binding.navigationView
        navigationView.apply {
            setNavigationItemSelectedListener(navigationChangeListener)
            setCheckedItem(R.id.all_home_work)
        }

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_navigation, R.string.close_navigation)
        val drawable = DrawerArrowDrawable(applicationContext)
        drawable.color = Color.parseColor("#FFFFFF")
        toggle.drawerArrowDrawable = drawable
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private val navigationChangeListener = OnNavigationItemSelectedListener {item ->
        when (item.itemId) {
            R.id.all_home_work -> {
                findNavController(R.id.nav_fragment_host).navigate(R.id.fragment_rasp2)
                true
            }
            R.id.archive_nav_menu -> {
                true
            }
            R.id.admin_button_nav_menu -> {
                findNavController(R.id.nav_fragment_host).navigate(R.id.action_fragment_rasp2_to_fragmentAdminPassword)
                true
            }
            else -> true
        }
    }
}