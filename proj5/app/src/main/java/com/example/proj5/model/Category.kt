package com.example.proj5.model
//from proj4
import java.util.UUID
//java util UUID allows a unique identifier, so when category is created it gets assigned so delete and column can use

//holds data for one cat
data class Category(
    val id: String = UUID.randomUUID().toString(), val name: String
)