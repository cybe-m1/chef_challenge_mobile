package chefchallenge.frontend.mobile.ui.components.ingredients

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import chefchallenge.frontend.mobile.data.entities.Ingredient

@Composable
fun IngredientScreen(viewModel: IngredientViewModel) {

    val scaffoldState = rememberScaffoldState()

    val ingredients = viewModel.getAllIngredients()

    when (val state = viewModel.state.collectAsState().value) {
        is State.Loading ->
            LoadingBar()
        is State.Failed -> {
            LaunchedEffect(scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(
                    "Rien à afficher",
                    duration = SnackbarDuration.Long
                )
            }
        }
        is State.Success -> Column {

        }
    }
}