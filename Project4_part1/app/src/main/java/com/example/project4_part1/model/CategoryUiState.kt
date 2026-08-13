package com.example.project4_part1.model

//what the screen needs to render pg 

//4 vars, exposes one state obj .
//decribe entire screen state
data class CategoryUiState(
    val cityName: String = "San Jose", val category: List<Category> = emptyList(), val newCategory: String= "", val errorMsg: String? = null
)
