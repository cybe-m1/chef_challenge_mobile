package chefchallenge.frontend.mobile.presentation.Main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import chefchallenge.frontend.mobile.presentation.BottomNavigationItem
import chefchallenge.frontend.mobile.presentation.BottomNavigationMenu
import chefchallenge.frontend.mobile.ui.components.ingredients.IngredientViewModel

@Composable
fun IngredientScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()

    val viewModel: IngredientViewModel = hiltViewModel()

    val ingredients = viewModel.getAllIngredients()



    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text="Ingredient Screen")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.INGREDIENTS, navController = navController)
    }
}