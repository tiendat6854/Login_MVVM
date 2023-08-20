package com.example.loginmvvm.repository

import com.example.loginmvvm.model.User

interface IUserRepository {
    fun readUserData(): List<User>
    fun saveUserData(username: String, password: String)
}
