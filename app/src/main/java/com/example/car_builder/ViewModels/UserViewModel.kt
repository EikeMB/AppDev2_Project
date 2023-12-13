package com.example.car_builder.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.car_builder.AppUser
import com.example.car_builder.CRUD.UserRepositoryFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepositoryFirestore): ViewModel() {
    private val _activeUser = MutableStateFlow(AppUser("", "", 0,""))
    val activeUser: StateFlow<AppUser> = _activeUser.asStateFlow()

    private val _allUsers = MutableStateFlow(listOf<AppUser>())
    val allUsers: StateFlow<List<AppUser>> = _allUsers.asStateFlow()


    suspend fun getAllUsers(){
        viewModelScope.launch {
            userRepository.getUsers().collect() { users ->
                _allUsers.value = users
            }
        }
    }

    suspend fun getUser(user: String){
        viewModelScope.launch {
            userRepository.getUser(user).collect() { user ->
                _activeUser.value = user
            }
        }
    }

    fun addUser(user: AppUser){
        viewModelScope.launch {
            userRepository.addUser(user)
        }
    }


    fun deleteUser(user: AppUser){
        viewModelScope.launch{
            userRepository.delete(user)
        }
    }
}