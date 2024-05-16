package net.ezra.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.ezra.ui.SplashScreen
import net.ezra.ui.about.AboutScreen
import net.ezra.ui.auth.LoginScreen
import net.ezra.ui.auth.SignUpScreen
import net.ezra.ui.category.CategoryScreen
import net.ezra.ui.dashboard.DashboardScreen
//import net.ezra.ui.auth.SignupScreen
import net.ezra.ui.home.HomeScreen
import net.ezra.ui.location.LocationScreen
import net.ezra.ui.profiles.ProfilesScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_HOME


) {
    BackHandler {
        navController.popBackStack()

        }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {




//        composable(ROUTE_SIGNUP) {
//            SignupScreen(navController)
//        }


        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }


        composable(ROUTE_ABOUT) {
            AboutScreen(navController)
        }




        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }



        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(ROUTE_REGISTER) {
           SignUpScreen(navController = navController) {

           }
        }

        composable(ROUTE_LOGIN) {
            LoginScreen(navController = navController){}
        }
        composable(ROUTE_LOCATION){
            LocationScreen(navController )
        }

        composable(ROUTE_PROFILES){
           ProfilesScreen(navController )
        }


        composable(ROUTE_REGISTRATION){
            ProfilesScreen(navController )
        }

        composable(ROUTE_CATEGORY){
           CategoryScreen(navController )
        }




































    }
}