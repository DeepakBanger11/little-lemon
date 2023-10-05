package com.littlelemon.liitlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.littlelemon.liitlelemon.Home
import com.littlelemon.liitlelemon.Profile
import com.littlelemon.liitlelemon.Onboarding
import com.littlelemon.liitlelemon.composables.Home
import com.littlelemon.liitlelemon.composables.Onboarding
import com.littlelemon.liitlelemon.composables.Profile


@Composable
fun NavigationComposable(context: Context,navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = checkStartDestination(context)
    ) {
        composable(Home.route) {
            Home(navController)
        }
        composable(Profile.route) {
            Profile(navController)
        }
        composable(Onboarding.route) {
            Onboarding(context,navController)
        }
    }
}

private fun checkStartDestination(context: Context): String {
    //check data is stored in shared preferences and return the appropriate destination
    // For example, if user data exists, returns Onboarding
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)


    if (sharedPreferences.getBoolean("userRegistered", false)) {
        return Home.route
    }
    else{
        return Onboarding.route
    }
}