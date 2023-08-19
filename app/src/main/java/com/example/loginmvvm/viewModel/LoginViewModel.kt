package com.example.loginmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.loginmvvm.MyApplication
import com.example.loginmvvm.model.User
import com.example.loginmvvm.repository.UserRepository
import com.example.loginmvvm.view.MainActivity

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    fun checkAccountValid(username: String, password: String) {
        val userDataList = userRepository.readUserData()
        val isValid = userDataList.any { it.username == username && it.password == password }
        _loginSuccess.value = isValid
    }

    companion object Factory {
        @Suppress("UNCHECKED_CAST")
        val Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(
                    UserRepository(MyApplication.getApplicationContext())
                ) as T
            }
        }
    }
}

