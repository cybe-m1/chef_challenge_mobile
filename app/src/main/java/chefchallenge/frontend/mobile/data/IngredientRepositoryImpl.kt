package chefchallenge.frontend.mobile.data.repositories

import chefchallenge.frontend.mobile.domain.model.Ingredient
import chefchallenge.frontend.mobile.domain.model.State
import chefchallenge.frontend.mobile.util.Constants
import chefchallenge.frontend.mobile.util.Response
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IngredientRepositoryImpl @Inject constructor() {
    private val mIngredientsCollection = Firebase.firestore.collection(Constants.COLLECTION_NAME_INGREDIENTS)

    fun getAllIngredientsData(): Flow<State<List<Ingredient>>> = callbackFlow {
        trySend(State.loading())

        val ingredientDocument = mIngredientsCollection

        val subscription = ingredientDocument.addSnapshotListener { snapshot, exception ->
            exception?.let {
                trySend(State.failed(it.message.toString()))
                cancel(it.message.toString())
            }

            snapshot?.let {
                val list: List<Ingredient> = it.documents.map { it.toObject(Ingredient::class.java)!! }
                trySend(State.success(list)).isSuccess
            } ?: trySend(State.failed("Not found")).isFailure
        }

        awaitClose { subscription.remove() }
    }
}