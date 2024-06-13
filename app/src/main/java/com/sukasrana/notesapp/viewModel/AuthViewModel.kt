package com.sukasrana.notesapp.viewModel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.sukasrana.notesapp.data.local.authLogin.AuthRepository
import com.sukasrana.notesapp.data.local.authLogin.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginFlow= MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow


    private val _signUpFlow= MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null){
            _loginFlow.value=Resource.Success(repository.currentUser!!)
        }
    }

    fun login(email: String,password:String)=viewModelScope.launch {

        val result = repository.login(email,password)
        _loginFlow.value=result
    }

    fun signup(name:String,email: String,password:String)=viewModelScope.launch {

        val result = repository.signup(name,email,password)
        _signUpFlow.value=result
    }

    fun logout(){
        repository.logout()
        _loginFlow.value=null
        _signUpFlow.value=null

    }
}