package chefchallenge.frontend.mobile.data.repositories

import chefchallenge.frontend.mobile.data.entities.Ingredient
import chefchallenge.frontend.mobile.data.entities.State
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IngredientRepository @Inject constructor() {
    private val mIngredientsCollection = Firebase.firestore.collection("INGREDIENTS")

    fun getAllIngredientsData(): Flow<State<List<Ingredient>>> = callbackFlow {
        trySend(State.Loading())

        val ingredientDocument = mIngredientsCollection

        val subscription = ingredientDocument.addSnapshotListener { snapshot, exception ->
            exception?.let {
                trySend(State.Failed(it.message.toString()))
                cancel(it.message.toString())
            }

            snapshot?.let {
                val list: List<Ingredient> = it.documents.map { it.toObject(Ingredient::class.java)!! }
                trySend(State.Success(list)).isSuccess
            } ?: trySend(State.Failed("Not found")).isFailure
        }

        awaitClose { subscription.remove() }
    }
}