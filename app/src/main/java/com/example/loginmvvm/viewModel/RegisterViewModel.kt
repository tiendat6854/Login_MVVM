package com.example.loginmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.model.User

class RegisterViewModel : ViewModel() {
    private val _accountExist = MutableLiveData<Boolean>()
    val accountExist: LiveData<Boolean>
        get() = _accountExist

    fun isAccountExist(username: String, userDataList: List<User>) {
        _accountExist.value = userDataList.any { it.username == username }
    }
}
