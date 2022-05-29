package chefchallenge.frontend.mobile.util

sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash_screen")
    object LoginScreen: Screens("login_screen")
    object LogoutScreen: Screens("logout_screen")
    object SignUpScreen: Screens("signup_screen")
    object HomepageScreen: Screens("homepage_screen")
    object ReceipeScreen: Screens("receipe_screen")
    object IngredientScreen: Screens("ingredient_screen")
    object ProfileScreen: Screens("profile_screen")
}
