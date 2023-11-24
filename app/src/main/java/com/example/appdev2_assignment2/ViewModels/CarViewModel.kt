package com.example.appdev2_assignment2.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.appdev2_assignment2.CRUD.CarRepository
import com.example.appdev2_assignment2.Car
import com.example.appdev2_assignment2.User
import com.example.appdev2_assignment2.cars
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CarViewModel(private val carRepository: CarRepository) : ViewModel(){

    private val _userCars = MutableStateFlow(listOf<Car>())
    val userCars: StateFlow<List<Car>> = _userCars.asStateFlow()


    private val _allCars = MutableStateFlow(listOf<Car>())
    val allCars: StateFlow<List<Car>> = _allCars.asStateFlow()
    suspend fun getCarsForUser(user: User){
        viewModelScope.launch {
            carRepository.getCars(user).collect() { userCars ->
                _userCars.value = userCars
            }
        }
    }

    // LiveData for observing changes in the list of all cars
    suspend fun getAllCars(){
        viewModelScope.launch {
            carRepository.getCars().collect() { allCars ->
                _allCars.value = allCars
            }
        }
    }
    // Function to add a car
    fun addCar(car: Car) {
        viewModelScope.launch {
            carRepository.addCar(car)
        }
    }

    // Function to delete a car
    fun deleteCar(car: Car) {
        viewModelScope.launch{
            carRepository.delete(car)
        }
    }


}

