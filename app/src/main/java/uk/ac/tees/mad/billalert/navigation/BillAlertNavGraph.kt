package uk.ac.tees.mad.billalert.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun BillAlertNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {
        composable(NavRoutes.Splash.route) {

        }

        composable(NavRoutes.Auth.route) {

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