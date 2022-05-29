package chefchallenge.frontend.mobile.domain.user_cases

import chefchallenge.frontend.mobile.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseAuthState @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()
}