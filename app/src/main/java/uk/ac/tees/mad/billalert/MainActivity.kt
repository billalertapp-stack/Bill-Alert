package uk.ac.tees.mad.billalert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.billalert.navigation.BillAlertNavGraph
import uk.ac.tees.mad.billalert.ui.theme.BillAlertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BillAlertTheme {
                val navController = rememberNavController()
                BillAlertNavGraph(navController = navController)
            }
        }
    }
}