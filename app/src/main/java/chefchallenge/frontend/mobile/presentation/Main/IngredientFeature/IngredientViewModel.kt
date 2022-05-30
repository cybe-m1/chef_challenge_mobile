package chefchallenge.frontend.mobile.presentation.Main.IngredientFeature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chefchallenge.frontend.mobile.data.repositories.IngredientRepositoryImpl
import chefchallenge.frontend.mobile.domain.model.Ingredient
import chefchallenge.frontend.mobile.domain.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
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

    private val mState = MutableStateFlow<State<List<Ingredient>>>(State.loading())
    val state: StateFlow<State<List<Ingredient>>>
        get() = mState

    private fun getAllIngredients() {
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