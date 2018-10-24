package club.scorpio.univ.scorpio

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import club.scorpio.univ.scorpio.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setFirstLayout()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onRestart() {
        super.onRestart()
        setFirstLayout()
    }

    override fun onResume() {
        super.onResume()
        setFirstLayout()
    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if(! nav_view.menu.getItem(0).isChecked)
                setFirstLayout()
            else
                showAlert()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        //menu.findItem(R.id.nav_home).setEnabled(true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.join_us -> {
                Toast.makeText(this, "Please login", Toast.LENGTH_SHORT)
                        .show()
                startActivity(Intent(this, LoginActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_settings -> startActivity(Intent(this, SettingsActivity::class.java))
        }
        // Handle navigation view item clicks here.
        var fragment: Fragment = when (item.itemId) {

            R.id.nav_home -> Home()
            R.id.nav_course -> Courses()
            R.id.nav_slideshow -> SlideShow()
            R.id.nav_events -> Events()
            R.id.nav_about_us -> AboutUs()

            else -> Home()
        }
        var fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .replace(R.id.linearlayout, fragment)
                .commit()
        item.isChecked = true
        item.title

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setFirstLayout(){
        var frag: Fragment = Home()
        var fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .replace(R.id.linearlayout, frag)
                .commit()

        nav_view.menu.getItem(0).isChecked = true
        nav_view.menu.getItem(1).isChecked = false
        nav_view.menu.getItem(2).isChecked = false
        nav_view.menu.getItem(3).isChecked = false
    }

    private fun showAlert(){

        var builder = AlertDialog.Builder(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setTitle("Exit !")
                    .setIcon(R.drawable.ic_exit_to_app_black_24dp)
                    .setCancelable(false)
                    .setMessage("Do you really want to exit ?")
                    .setPositiveButton("Yes"){
                        dialog, which -> finish()
                    }
                    .setNegativeButton("No"){
                        dialog, which -> dialog.dismiss()
                    }
                    //.setPositiveButtonIcon(getDrawable(R.drawable.ic_check_box_black_24dp))
                    //.setNegativeButtonIcon(getDrawable(R.drawable.ic_cancel_black_24dp))
                    .show()
        }
    }
}
