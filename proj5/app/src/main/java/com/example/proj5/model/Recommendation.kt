package com.example.proj5.model

/*
reccomendation file is to show one plave in the category
\stores the cat is belongs to, name, detail etc,

 */

import java.util.UUID

data class Recommendation(
    val id: String=UUID.randomUUID().toString(),val categoryId:String, val name:String, val details: String? =null, val address: String? = null
)