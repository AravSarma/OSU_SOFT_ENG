package com.example.proj5
//similar to mine from proj4
/*
entry point,  sets content to city app,
 builds and drives the screens
 */
//sources:
//https://developer.android.com/reference/androidx/navigation/NavHost
//https://developer.android.com/training/constraint-layout/motionlayout/ref/onclick?hl=en



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.proj5.ui.CityApp
import com.example.proj5.ui.theme.Proj5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proj5Theme{
                Surface(modifier = Modifier.fillMaxSize()) {
                    CityApp()
                }
            }
        }
    }
}


