package com.example.proj5.ui


import androidx.lifecycle.ViewModel
import com.example.proj5.model.Category
import com.example.proj5.model.CityUiState
import com.example.proj5.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.asStateFlow

//this file operates everythign, holds the state and funcs


class CityViewModel: ViewModel() {
    //putting underscore to diffferentiate priv
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    //going to add init to preload starte4d data, loads upon launch so it isnt empty.
    init {
        initialData()
    }
//from p4
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
        _uiState.update{state->state.copy(category=state.category.filterNot{it.id ==category.id})}
    }
    //new funcs

    fun recommendCategory(categoryId: String): List<Recommendation> {
        return _uiState.value.recommendations.filter { it.categoryId == categoryId }
    }
    fun newRecommendation(input:String){
        _uiState.update { it.copy(newRecommendation = input, recommendationErrorMsg = null) }
    }
    //add, if it is dup, exists , if not add
    fun addRecommendation(categoryId: String) {
        val name = _uiState.value.newRecommendation.trim()
        if(name.isEmpty()) {
            _uiState.update {it.copy(recommendationErrorMsg = "cant be empty") }
            return
        }
        val isDup = _uiState.value.recommendations.any {
            it.categoryId == categoryId && it.name.equals(name, ignoreCase = true)
        }
        if (isDup) {
            _uiState.update { it.copy(recommendationErrorMsg = "\"$name\"exists in this category") }
            return
        }
        _uiState.update {
            it.copy(
                recommendations = it.recommendations + Recommendation(categoryId = categoryId, name = name),
                newRecommendation = "",
                recommendationErrorMsg = null
            )
        }
    }
    fun deleteRecommendation(recommendation: Recommendation) {
        _uiState.update { state->state.copy(recommendations=state.recommendations.filterNot {it.id==recommendation.id})
        }
    }
    fun getRecommendation(recommendationId: String):Recommendation? {
        return _uiState.value.recommendations.find {it.id==recommendationId}
    }
    fun InputChanged(input: String) {
        _uiState.update { it.copy(newDetailInput = input) }
    }
    fun saveDetail(recommendationId: String) {
        val detailText = _uiState.value.newDetailInput.trim()
        if (detailText.isEmpty()) return
        _uiState.update { state->state.copy(
                recommendations = state.recommendations.map{
                    if (it.id == recommendationId) it.copy(details = detailText) else it
                },newDetailInput = ""
            )
        }
    }
    //used ai to get initial data fed from places near my house
    private fun initialData() {
        val food = Category(name = "Food")
        val parks = Category(name = "Parks")
        val coffee = Category(name = "Coffee")

        val recs = listOf(
            Recommendation(
                categoryId = food.id,
                name = "Original Joe's",
                details = "Classic San Jose Italian-American spot, open late, great for groups.",
                address = "301 S 1st St, San Jose, CA"
            ),
            Recommendation(categoryId = food.id, name = "Pho So 1"),
            Recommendation(categoryId = food.id, name = "La Victoria Taqueria"),
            Recommendation(
                categoryId = parks.id,
                name = "Guadalupe River Park",
                details = "Long paved trail through downtown, good for a run or a walk.",
                address = "438 W San Fernando St, San Jose, CA"
            ),
            Recommendation(categoryId = parks.id, name = "Alum Rock Park"),
            Recommendation(categoryId = parks.id, name = "Kelley Park"),
            Recommendation(
                categoryId = coffee.id,
                name = "Voyager Craft Coffee",
                details = "Small local roaster, quiet enough to work from.",
                address = "1091 The Alameda, San Jose, CA"
            ),
            Recommendation(categoryId = coffee.id, name = "Chromatic Coffee"),
            Recommendation(categoryId = coffee.id, name = "Roy's Station Coffee")
        )

        _uiState.update {
            it.copy(category = listOf(food, parks, coffee), recommendations = recs)
        }
    }


}


