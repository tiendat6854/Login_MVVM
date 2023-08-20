package com.example.loginmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val IUserRepository: IUserRepository) : ViewModel() {

    private val _accountExist = MutableLiveData<Boolean>()
    val accountExist: LiveData<Boolean> = _accountExist

    fun registerUser(username: String, password: String) {
        val userDataList = IUserRepository.readUserData()
        val isAccountExist = userDataList.any { it.username == username }
        if (isAccountExist) {
            _accountExist.value = true
        } else {
            IUserRepository.saveUserData(username, password)
            _accountExist.value = false
        }
    }
}
