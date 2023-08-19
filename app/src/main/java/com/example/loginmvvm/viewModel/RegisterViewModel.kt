package com.example.loginmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.MyApplication
import com.example.loginmvvm.model.User
import com.example.loginmvvm.repository.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _accountExist = MutableLiveData<Boolean>()
    val accountExist: LiveData<Boolean>
        get() = _accountExist

    fun checkAccountExist(username: String, password: String) {
        val userDataList = userRepository.readUserData()
        val isAccountExist = userDataList.any { it.username == username }
        if (isAccountExist) {
            _accountExist.value = true
        } else {
            userRepository.saveUserData(username, password)
            _accountExist.value = false
        }
    }

    companion object Factory {
        @Suppress("UNCHECKED_CAST")
        val Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RegisterViewModel(
                    UserRepository(MyApplication.getApplicationContext())
                ) as T
            }
        }
    }
}
