package chefchallenge.frontend.mobile.ui.components.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chefchallenge.frontend.mobile.data.entities.Ingredient
import chefchallenge.frontend.mobile.data.entities.State
import chefchallenge.frontend.mobile.data.repositories.IngredientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IngredientViewModel @Inject constructor (
    private val repository: IngredientRepository
): ViewModel() {
    init {
        getAllIngredients()
    }

    private val mState = MutableStateFlow<State<List<Ingredient>>>(State.loading())
    val state: StateFlow<State<List<Ingredient>>>
        get() = mState

    fun getAllIngredients() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getAllIngredientsData().collect {
                    mState.value = it
                }
            } catch (ex: Exception) {
                mState.value = State.failed(ex.localizedMessage)
            }
        }
    }
}