package chefchallenge.frontend.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import chefchallenge.frontend.mobile.presentation.Authentication.AuthenticationViewModel
import chefchallenge.frontend.mobile.presentation.Authentication.LoginScreen
import chefchallenge.frontend.mobile.presentation.Authentication.SignupScreen
import chefchallenge.frontend.mobile.presentation.Main.HomepageScreen
import chefchallenge.frontend.mobile.presentation.Main.IngredientFeature.IngredientScreen
import chefchallenge.frontend.mobile.presentation.Main.ReceipeScreen
import chefchallenge.frontend.mobile.presentation.Profile.ProfileScreen
import chefchallenge.frontend.mobile.presentation.SplashScreen
import chefchallenge.frontend.mobile.ui.theme.MobileTheme
import chefchallenge.frontend.mobile.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: AuthenticationViewModel = hiltViewModel()

                    ChefChallengeApp(navController, authViewModel)
                }
            }
        }
    }
}

@Composable
fun ChefChallengeApp(navController: NavHostController, authViewModel: AuthenticationViewModel) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(route=Screens.LoginScreen.route) {
            LoginScreen(navController, authViewModel)
        }
        composable(route=Screens.SignUpScreen.route) {
            SignupScreen(navController, authViewModel)
        }
        composable(route=Screens.SplashScreen.route) {
            SplashScreen(navController, authViewModel)
        }
        composable(route=Screens.HomepageScreen.route) {
            HomepageScreen(navController)
        }
        composable(route=Screens.ProfileScreen.route) {
            ProfileScreen(navController, authViewModel)
        }
        composable(route=Screens.ReceipeScreen.route) {
            ReceipeScreen(navController)
        }
        composable(route=Screens.IngredientScreen.route) {
            IngredientScreen(navController)
        }
    }
}