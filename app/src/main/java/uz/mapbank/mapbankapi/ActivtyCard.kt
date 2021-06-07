package uz.mapbank.mapbankapi

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.budiyev.android.codescanner.CodeScanner
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class ActivtyCard : AppCompatActivity() {
    val navController by lazy {
        Navigation.findNavController(this,
            R.id.nav_host_fragment) }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        swipeItem()


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_slideshow,
                R.id.exchange_navigation,
                R.id.nav_mycard,
                R.id.loan_navigation,
                R.id.money_navigation
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun swipeItem() {
        /*swipe = Swipe()
        swipe.setListener(object : SwipeListener() {
            fun onSwipingLeft(event: MotionEvent?) {
                SystemColor.info.setText("SWIPING_LEFT")
            }

            fun onSwipedLeft(event: MotionEvent?) {
                SystemColor.info.setText("SWIPED_LEFT")
            }

            fun onSwipingRight(event: MotionEvent?) {
                SystemColor.info.setText("SWIPING_RIGHT")
            }

            fun onSwipedRight(event: MotionEvent?) {
                SystemColor.info.setText("SWIPED_RIGHT")
            }

            fun onSwipingUp(event: MotionEvent?) {
                SystemColor.info.setText("SWIPING_UP")
            }

            fun onSwipedUp(event: MotionEvent?) {
                SystemColor.info.setText("SWIPED_UP")
            }

            fun onSwipingDown(event: MotionEvent?) {
                SystemColor.info.setText("SWIPING_DOWN")
            }

            fun onSwipedDown(event: MotionEvent?) {
                SystemColor.info.setText("SWIPED_DOWN")
            }
        })*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activty_card, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when (item.itemId){
                 R.id.action_scanner ->{
                     navController.navigate(R.id.qrcode_navigation)
                     true
                 }
                 R.id.action_sms->{true}
             else->super.onOptionsItemSelected(item)
         }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}