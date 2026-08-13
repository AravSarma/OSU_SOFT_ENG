package com.example.proj5.model

//similar to p4 aded the new fields from viewmodel

//what the screen needs to render pg

//4 vars, exposes one state obj .
//decribe entire screen state
data class CityUiState(
    val cityName: String = "San Jose", val category: List<Category> = emptyList(),val newCategory: String = "",val recommendations: List<Recommendation> = emptyList(),
    val errorMsg: String? = null,val newRecommendation: String = "",val newDetailInput: String = "",val recommendationErrorMsg: String? = null

)
