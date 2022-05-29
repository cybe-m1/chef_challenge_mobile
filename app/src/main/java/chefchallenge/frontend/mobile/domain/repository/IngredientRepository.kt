package chefchallenge.frontend.mobile.domain.repository

interface IngredientRepository {
    fun getIngredient();
    fun getAllIngredients();
    fun getIngredientByName();
}