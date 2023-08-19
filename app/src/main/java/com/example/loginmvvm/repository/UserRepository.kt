package com.example.loginmvvm.repository

import android.content.Context
import com.example.loginmvvm.model.User
import java.io.BufferedReader
import java.io.InputStreamReader

class UserRepository(private val context: Context) {

    fun readUserData(): List<User> {
        val userDataList = mutableListOf<User>()
        try {
            val fileInputStream = context.openFileInput("user_data.csv")
            val reader = BufferedReader(InputStreamReader(fileInputStream))
            reader.forEachLine { line ->
                val split = line.split(",")
                if (split.size == 2) {
                    val username = split[0]
                    val password = split[1]
                    userDataList.add(User(username, password))
                }
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return userDataList
    }

    fun saveUserData(username: String, password: String) {
        val userData = "$username,$password\n"
        try {
            val fileOutputStream = context.openFileOutput("user_data.csv", Context.MODE_APPEND)
            fileOutputStream.write(userData.toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
