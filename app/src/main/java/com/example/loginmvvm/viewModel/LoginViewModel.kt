package com.example.loginmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.model.User

class LoginViewModel : ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    fun isAccountValid(username: String, password: String, userDataList: List<User>) {
        val isValid = userDataList.any { it.username == username && it.password == password }
        _loginSuccess.value = isValid
    }
}

