package chefchallenge.frontend.mobile.ui.components.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chefchallenge.frontend.mobile.data.repositories.IngredientRepositoryImpl
import chefchallenge.frontend.mobile.domain.model.Ingredient
import chefchallenge.frontend.mobile.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IngredientViewModel @Inject constructor (
    private val repository: IngredientRepositoryImpl
): ViewModel() {
    init {
        getAllIngredients()
    }

    private val mState = MutableStateFlow<Response<List<Ingredient>>>(Response.Loading)
    val state: StateFlow<Response<List<Ingredient>>>
        get() = mState

    fun getAllIngredients() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getAllIngredientsData().collect {
                    mState.value = it
                }
            } catch (ex: Exception) {
                mState.value = Response.Error(ex.localizedMessage)
            }
        }
    }
}