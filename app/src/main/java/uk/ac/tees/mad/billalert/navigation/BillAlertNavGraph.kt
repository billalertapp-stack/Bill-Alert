package uk.ac.tees.mad.billalert.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uk.ac.tees.mad.billalert.ui.screens.AuthScreen
import uk.ac.tees.mad.billalert.ui.screens.SplashScreen

@Composable
fun BillAlertNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {
        composable(NavRoutes.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToAuth = {
                    navController.navigate(NavRoutes.Auth.route) {
                        popUpTo(NavRoutes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.Auth.route) {
            AuthScreen(
                onAuthSuccess = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.Home.route) {

        }

        // ✅ Argument passing: billId passed via nav arg, nullable
        composable(
            route = NavRoutes.AddEditBill.route,
            arguments = listOf(
                navArgument(NavRoutes.AddEditBill.ARG_BILL_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val billId = backStackEntry.arguments?.getString(NavRoutes.AddEditBill.ARG_BILL_ID)

        }

        composable(NavRoutes.Settings.route) {

        }
    }
}