package chefchallenge.frontend.mobile.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import chefchallenge.frontend.mobile.data.repositories.IngredientRepository
import chefchallenge.frontend.mobile.ui.components.ingredients.IngredientScreen
import chefchallenge.frontend.mobile.ui.components.ingredients.IngredientViewModel
import chefchallenge.frontend.mobile.ui.custom.navigation.BottomNavigationBar
import chefchallenge.frontend.mobile.ui.theme.ComposableSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableSampleTheme {
                ChefChallengeApp()
            }
        }
    }
}

@Preview
@Composable
private fun ChefChallengeApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavHost(navController, startDestination = NavigationKeys.Route.HOMEPAGE) {
            composable(route = NavigationKeys.Route.HOMEPAGE) {
                HomepageScreenDestination(navController)
            }

            composable(route = NavigationKeys.Route.RECEIPE) {
                ReceipeScreenDestination(navController)
            }
            composable(route = NavigationKeys.Route.USER) {
                UserScreenDestination()
            }
            composable(route = NavigationKeys.Route.INGREDIENTS) {
                IngredientsScreenDestination()
            }
        }
    }
}

@Composable
private fun HomepageScreenDestination(navController: NavController) {
    Text(text = "Homepage screen")

}

@Composable
private fun ReceipeScreenDestination(navController: NavController) {
    Text(text = "Receipe screen")

}

@Composable
private fun UserScreenDestination() {
    Text(text = "User screen")
}

@Composable
private fun IngredientsScreenDestination() {
    IngredientScreen(viewModel = IngredientViewModel(IngredientRepository()))
}


object NavigationKeys {

    object Route {
        const val HOMEPAGE = "homepage"
        const val RECEIPE = "receipe"
        const val USER = "user"
        const val INGREDIENTS = "ingredients"
    }
}