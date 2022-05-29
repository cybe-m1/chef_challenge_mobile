package chefchallenge.frontend.mobile.domain.repository

import chefchallenge.frontend.mobile.util.Response
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun isUserAuthenticatedInFirebase(): Boolean

    fun getFirebaseAuthState() : Flow<Boolean>

    fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>>

    fun firebaseSignOut(): Flow<Response<Boolean>>

    fun firebaseSignUp(username: String, email: String, password: String): Flow<Response<Boolean>>
}