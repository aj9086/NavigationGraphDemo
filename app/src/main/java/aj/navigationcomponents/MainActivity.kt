package aj.navigationcomponents

import aj.navigationcomponents.base.BaseActivity
import aj.navigationcomponents.home_module.*
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    internal lateinit var drawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavigation()
    }

    private fun setNavigation() {
        // Set up ActionBar
        setSupportActionBar(toolbar)


        navController = findNavController(this, R.id.nav_host)
        //        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)   /// set axn bar title with navigation label text and back icon by default

        // Set up navigation menu
        //        NavigationUI.setupWithNavController(nav_view,navController)

        //Setting the navigation controller to Bottom Nav
        //          NavigationUI.setupWithNavController(bottomNavBar,navController)


        bottomNavBar.setOnNavigationItemSelectedListener(bottomNavSelectedListner)
        nav_view.setNavigationItemSelectedListener(navSelectedListner)

        drawerToggle = object :
            ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open, R.string.close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }
        }

        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        toolbar.setNavigationOnClickListener {
            val currentFragment = nav_host.childFragmentManager.fragments[0]
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                //                super.onBackPressed()
                when {
                    currentFragment is HomeScreen || currentFragment is GalleryScreen || currentFragment is SettingsScreen || currentFragment is ProfileScreen -> drawer_layout.openDrawer(
                        GravityCompat.START
                    )
                    nav_host.childFragmentManager.backStackEntryCount >= 1 -> navController.popBackStack()
                    else -> {
                        if (doubleBackToExitPressedOnce) {
                            finish()
                            finishAffinity()
                        }
                        this.doubleBackToExitPressedOnce = true
                        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT)
                            .show()
                        Handler().postDelayed(
                            Runnable { doubleBackToExitPressedOnce = false },
                            2000
                        )
                    }
                }
            }
        }
    }


    //Setting Up the back button
//    override fun onSupportNavigateUp()= NavHostFragment.findNavController(nav_host).navigateUp()
    /* override fun onSupportNavigateUp(): Boolean {
         return NavigationUI.navigateUp(findNavController(this, R.id.nav_host), drawer_layout) || super.onSupportNavigateUp()

     }*/
    /*  override fun onSupportNavigateUp(): Boolean {
          return NavigationUI.navigateUp(
              navController, drawer_layout
          )
      }*/

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        val currentFragment = nav_host.childFragmentManager.fragments[0]
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
//            super.onBackPressed()
            when {
//                currentFragment is ProfileScreen -> navController.navigate(R.id.homeScreen)
                nav_host.childFragmentManager.backStackEntryCount >= 1 -> navController.popBackStack()
                else -> {
                    if (doubleBackToExitPressedOnce) {
                        finish()
                        finishAffinity()
                    }
                    this.doubleBackToExitPressedOnce = true
                    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT)
                        .show()
                    Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
                }
            }
        }
    }

    fun setToolbar(
        title: String,
        icon: Int,
        isBackShown: Boolean,
        isBottomBarShown: Boolean,
        isDrawerShown: Boolean
    ) {
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        titleTB.text = title

        if (isDrawerShown) {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        } else {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }

        if (isBackShown) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            toolbar.navigationIcon = resources.getDrawable(icon)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
            toolbar.navigationIcon = null
        }
        bottomBarVisibility(isBottomBarShown)

    }

    fun bottomBarVisibility(isBottomBarShown: Boolean) {
        if (isBottomBarShown) {
            bottomNavBar.visibility = View.VISIBLE
        } else {
            bottomNavBar.visibility = View.GONE
        }
    }

    private var bottomNavSelectedListner = BottomNavigationView.OnNavigationItemSelectedListener {
        it.isChecked = true
        when (it.itemId) {
            R.id.home -> {
                navController.navigate(
                    R.id.homeScreen,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.homeScreen, true).build()
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.gallery -> {
//                navController.navigate(HomeScreenDirections.actionHomeScreenToGalleryScreen(),null,NavOptions.Builder().setPopUpTo(R.id.galleryScreen2,true).build())
                /*
                * we can't use action_this_to_that bcz this specify that it will go from particular this frag to that frag
                * if we click again *gallery , then  it will crash bcz it doesn't specify the direction.
                * arg null means no arguments passed
                * NavOptions is used for preventing same screen in stack (if we click multiple times on *gallery, then it will not store multiple time in stack)
                * */
//                navController.navigate(R.id.galleryScreen2,null,NavOptions.Builder().setPopUpTo(R.id.galleryScreen2,true).build())
//                return@OnNavigationItemSelectedListener true

                ////  nested nav graph

                if (navController.currentDestination?.id == R.id.homeScreen) {
                    navController.navigate(
                        R.id.action_homeScreen_to_galleryNav,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.galleryScreen2, true).build()
                    )
                } else {
                    navController.navigate(R.id.homeScreen)
                    navController.navigate(
                        R.id.action_homeScreen_to_galleryNav,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.galleryScreen2, true).build()
                    )
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.setting -> {
                if (navController.currentDestination?.id == R.id.homeScreen) {
                    navController.navigate(
                        R.id.action_homeScreen_to_settingNav,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.settingsScreen, true).build()
                    )
                } else {
                    navController.navigate(R.id.homeScreen)
                    navController.navigate(
                        R.id.action_homeScreen_to_settingNav,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.settingsScreen, true).build()
                    )
                }
                return@OnNavigationItemSelectedListener true
//                navController.navigate(R.id.action_homeScreen_to_navigation,null,NavOptions.Builder().setPopUpTo(R.id.settingsScreen,true).build())
//                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }
    private var navSelectedListner = NavigationView.OnNavigationItemSelectedListener { menuItem ->
        menuItem.isChecked = false
//        drawer_layout.closeDrawers()
        drawer_layout.closeDrawer(GravityCompat.START)
        when (menuItem.itemId) {
            R.id.nav_profile -> {
                navController.navigate(
                    R.id.profileScreen,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.profileScreen, true).build()
                )
            }
            R.id.nav_logout -> {
//                navController.navigate(R.id.logout)
                if (navController.currentDestination?.id == R.id.homeScreen) {
                    navController.navigate(HomeScreenDirections.actionHomeScreenToLoginScreen())
                } else {
                    navController.navigate(R.id.homeScreen)
                    navController.navigate(HomeScreenDirections.actionHomeScreenToLoginScreen())
                }
            }
        }
        false
    }

}


