package com.example.project3.model
//to make the int hold string id
import android.content.pm.Capability
import androidx.annotation.StringRes

//data class to hold department num and capacity

data class Course(
    //ensure that its stored as text. others store cousrse and seat
    @StringRes val title: Int,val department:String,val number: Int,val capacity:Int
)