package com.example.project4_part1.ui

import androidx.lifecycle.ViewModel
import com.example.project4_part1.model.Category
import com.example.project4_part1.model.CategoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.asStateFlow

//this file operates everythign, holds the state and funcs


class CategoriesViewModel: ViewModel() {
    //putting underscore to diffferentiate priv
    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    fun onNewCategoryInputChanged(input: String){
        _uiState.update {it.copy(newCategory = input, errorMsg = null)}
    }
    fun addCategory(){
        val name = _uiState.value.newCategory.trim()
        //check empty name
        if(name.isEmpty()){
            _uiState.update{it.copy(errorMsg = "Cant be empty")}
            return
        }
        val isDup = _uiState.value.category.any{it.name.equals(name,ignoreCase = true)}
        if(isDup){
            _uiState.update {it.copy(errorMsg="\"$name\" already exists")}
            return
        }
        _uiState.update{
            it.copy(
                category = it.category+ Category(name=name), newCategory = "", errorMsg = null
            )
        }
    }
    fun deleteCat(category: Category){
        _uiState.update {state->state.copy(category=state.category.filterNot{it.id ==category.id})}
    }
}