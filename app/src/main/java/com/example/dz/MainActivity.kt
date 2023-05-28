package com.example.dz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.navigation.findNavController
import com.example.dz.databinding.ActivityMainBinding
import com.example.dz.screens.filter
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
        try {
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
        }catch (e: IllegalArgumentException){
            false
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.drawer_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.All -> filter("Всё")
            R.id.Russian -> filter("Русский язык")
            R.id.Literature -> filter("Литература")
            R.id.Math -> filter("Математика")
            R.id.History -> filter("История")
            R.id.PhysicalCulture -> filter("Физическая культура")
            R.id.OBJ -> filter("Основы безопасности жизнедеятельности")
            R.id.Astronomy -> filter("Астрономия")
            R.id.English -> filter("Иностранный язык")
            R.id.RLiterature -> filter("Родная литература")
            R.id.Informatics -> filter("Информатика")
            R.id.Physics -> filter("Физика")
            R.id.HumanAndSociety -> filter("Человек и общество")
            R.id.SZD -> filter("Социально-значимая деятельность")
            R.id.GCP -> filter("Общие компетенции профессионала")
            R.id.WorldSkills -> filter("Практикум по избранной компетенции Ворлдскиллс")
            R.id.Consultations -> filter("Консультации")
            R.id.Other -> filter("Другой")
        }
        return true
    }
    private fun filter(str: String){
        filter = str
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        findNavController(R.id.nav_fragment_host).navigate(R.id.fragment_rasp2)
    }
}