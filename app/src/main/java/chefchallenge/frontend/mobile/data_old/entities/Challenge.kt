package chefchallenge.frontend.mobile.data.entities

data class Challenge (
    val id: String,
    val name: String,
    val ingredients: List<Ingredient>
)