package chefchallenge.frontend.mobile.ui.components.ingredients

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import chefchallenge.frontend.mobile.data.entities.Ingredient

@Composable
fun IngredientScreen(viewModel: IngredientViewModel) {
    val scaffoldState = rememberScaffoldState()

    val ingredients = viewModel.getAllIngredients()
}