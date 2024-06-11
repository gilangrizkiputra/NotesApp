package com.sukasrana.notesapp.di.authLogin

import com.google.firebase.auth.FirebaseAuth
import com.sukasrana.notesapp.data.local.authLogin.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun provideFirebaseAuth():FirebaseAuth=FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseRepository(impl: AuthRepositoryImpl):AuthRepositoryImpl=impl
}