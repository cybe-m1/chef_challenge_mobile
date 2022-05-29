package chefchallenge.frontend.mobile.data

import chefchallenge.frontend.mobile.domain.model.User
import chefchallenge.frontend.mobile.domain.repository.AuthenticationRepository
import chefchallenge.frontend.mobile.util.Constants
import chefchallenge.frontend.mobile.util.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
): AuthenticationRepository {
    var operationSuccessful: Boolean = false

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }

        auth.addAuthStateListener(authStateListener)

        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }
            emit(Response.Success(operationSuccessful))
        } catch (e:Exception) {
            emit(Response.Error(e.localizedMessage?:"An Unexpected Error"))
        }
    }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        } catch (e:Exception) {
            emit(Response.Error(e.localizedMessage?:"An Unexpected Error"))
        }
    }

    override fun firebaseSignUp(
        username: String,
        email: String,
        password: String
    ): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }
            if (operationSuccessful) {
                val userid = auth.currentUser?.uid!!
                val obj = User(username = username, email = email, password = password, userid = userid)
                firestore.collection(Constants.COLLECTION_NAME_USERS).document(userid).set(obj).addOnSuccessListener {

                }
                emit(Response.Success(operationSuccessful))
            } else {
                emit(Response.Success(operationSuccessful))
            }
        } catch (e:Exception) {
            emit(Response.Error(e.localizedMessage?:"An Unexpected Error"))
        }
    }
}