package com.example.appdev2_assignment2.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdev2_assignment2.CRUD.CarPartRepository
import com.example.appdev2_assignment2.CRUD.CarPartRepositoryFirestore
import com.example.appdev2_assignment2.CarPart
import com.example.appdev2_assignment2.PartType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CarPartViewModel(private val carPartRepository: CarPartRepository): ViewModel() {

    private val _typeParts = MutableStateFlow(listOf<CarPart>())
    val typeParts: StateFlow<List<CarPart>> = _typeParts.asStateFlow()

    private val _allParts = MutableStateFlow(listOf<CarPart>())
    val allParts: StateFlow<List<CarPart>> = _allParts.asStateFlow()

    private val _activePart = MutableStateFlow(CarPart("", 0, 0, "", PartType.Body, 0))
    val activePart: StateFlow<CarPart> = _activePart.asStateFlow()

    suspend fun getPartsOfType(type: PartType){
        viewModelScope.launch {
            carPartRepository.getPartsOfType(type).collect() {typeParts ->
                _typeParts.value = typeParts
            }
        }
    }

    suspend fun getAllParts(){
        viewModelScope.launch {
            carPartRepository.getParts().collect() { parts ->
                _allParts.value = parts
            }
        }
    }

    suspend fun getPart(part: CarPart){
        viewModelScope.launch {
            carPartRepository.getPart(part.name).collect() { part ->
                _activePart.value = part
            }
        }
    }

    fun addPart(part: CarPart){
        viewModelScope.launch {
            carPartRepository.addCarPart(part)
        }
    }

    fun deletePart(part: CarPart){
        viewModelScope.launch {
            carPartRepository.delete(part)
        }
    }
}