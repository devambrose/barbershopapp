package com.example.barbershop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.barbershop.appointments.AppointmentFragment
import com.example.barbershop.appointments.AppointmentsFragment
import com.example.barbershop.customers.CustomersDashBoard
import com.example.barbershop.servicemanager.CreateServiceFragment
import com.example.barbershop.servicemanager.ServicesDashboard
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appToggleButton: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout =findViewById(R.id.drawerLayout)

        appToggleButton= ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open_nav,
            R.string.close_nav
        )

        val navigationMainView: NavigationView =findViewById(R.id.navigationMainView)

        drawerLayout.addDrawerListener(appToggleButton)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        changeFragment(this.supportFragmentManager,Dashboard(),Bundle())

        navigationMainView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.menu_services-> changeFragment(this.supportFragmentManager,
                    ServicesDashboard(),Bundle())

                R.id.menu_customers-> changeFragment(this.supportFragmentManager,
                CustomersDashBoard(),Bundle())

                R.id.menu_appointments-> changeFragment(this.supportFragmentManager,
                    AppointmentsFragment(),Bundle())
            }
            drawerLayout.closeDrawer(GravityCompat.START)

            true
        }

        val sharedPreferences=this.getSharedPreferences(
            Globals.APP_NAME,
            Context.MODE_PRIVATE
        )

        val userId = sharedPreferences.getInt(Globals.USER_ID,0)

        if (userId != 0) {
            val menu: Menu = navigationMainView.menu
            menu.findItem(R.id.menu_customers).isVisible = false
            menu.findItem(R.id.menu_services).isVisible = false
            menu.findItem(R.id.menu_appointments).isVisible = false
        }

        drawerLayout.addDrawerListener(appToggleButton)

        appToggleButton.syncState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.menu_log_off){
            val sharedPreferences=this.getSharedPreferences(
                Globals.APP_NAME,
                Context.MODE_PRIVATE
            )

            val editor=sharedPreferences.edit()

            editor.putBoolean(Globals.LOGGIN_STATUS, false)

            editor.apply()

            startActivity(Intent(this, SplashScreen::class.java))

            this.finish()

        }else if(appToggleButton.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}