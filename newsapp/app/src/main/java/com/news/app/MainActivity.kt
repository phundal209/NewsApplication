package com.news.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.news.app.hilt.entrypoint.MainActivityEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val entryPoint: MainActivityEntryPoint = EntryPointAccessors.
                    fromActivity(this, MainActivityEntryPoint::class.java)
        supportFragmentManager.fragmentFactory = entryPoint.getFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigate()
    }

    private fun navigate() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = host.navController
        navController.navigate(R.id.newsFragment)
    }
}
