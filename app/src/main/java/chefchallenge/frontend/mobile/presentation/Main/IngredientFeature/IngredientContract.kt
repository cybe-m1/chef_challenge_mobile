package chefchallenge.frontend.mobile.presentation.Main.IngredientFeature

import chefchallenge.frontend.mobile.domain.model.Ingredient

class IngredientContract {

    data class State(
        val ingredients: List<Ingredient> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}