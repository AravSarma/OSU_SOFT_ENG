package com.example.proj6.data
//using this as a data class for location and data
data class clueData (
    val id: Int, val clue: String, val hint: String, val targLat: Double, val targetLon: Double,
    val info: String, val geoRad: Double
)