package uk.ac.tees.mad.billalert.navigation

sealed class NavRoutes(val route: String) {
    // No args
    data object Splash : NavRoutes("splash")
    data object Auth : NavRoutes("auth")
    data object Home : NavRoutes("home")
    data object Settings : NavRoutes("settings")

    data object AddEditBill : NavRoutes("add_edit_bill?billId={billId}") {
        const val ARG_BILL_ID = "billId"
        fun createRoute(billId: String? = null): String =
            if (billId != null) "add_edit_bill?billId=$billId"
            else "add_edit_bill"
    }
}