package chefchallenge.frontend.mobile.di

import chefchallenge.frontend.mobile.data.AuthenticationRepositoryImpl
import chefchallenge.frontend.mobile.domain.repository.AuthenticationRepository
import chefchallenge.frontend.mobile.domain.user_cases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChefChallengeModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth, firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepositoryImpl) = AuthenticationUseCases (
        isUserAuthenticated = IsUserAuthenticated(repository),
        firebaseAuthState = FirebaseAuthState(repository),
        firebaseSignIn = FirebaseSignIn(repository),
        firebaseSignOut = FirebaseSignOut(repository),
        firebaseSignUp = FirebaseSignUp(repository)
    )
}