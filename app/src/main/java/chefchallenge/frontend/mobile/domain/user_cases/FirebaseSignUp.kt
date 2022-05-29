package chefchallenge.frontend.mobile.domain.user_cases

import chefchallenge.frontend.mobile.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String, username: String) = repository.firebaseSignUp(username, email, password)
}