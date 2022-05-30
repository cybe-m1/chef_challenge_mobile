package chefchallenge.frontend.mobile.domain.user_cases

import com.google.firebase.firestore.FirebaseFirestore

data class AuthenticationUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val firebaseAuthState: FirebaseAuthState,
    val firebaseSignUp: FirebaseSignUp,
    val firebaseSignIn: FirebaseSignIn,
    val firebaseSignOut: FirebaseSignOut
)
