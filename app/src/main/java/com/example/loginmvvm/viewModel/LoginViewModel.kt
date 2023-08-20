package com.example.loginmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val IUserRepository: IUserRepository) : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    fun checkAccountValid(username: String, password: String) {
        val userDataList = IUserRepository.readUserData()
        val isValid = userDataList.any { it.username == username && it.password == password }
        _loginSuccess.value = isValid
    }
}

