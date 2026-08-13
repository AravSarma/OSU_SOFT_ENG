package com.example.proj6.ui.screens
//perm req screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PermissionsScreen(onPermissionGranted: ()->Unit){
    //got ai to help with color and sizing

    Box(
        modifier = Modifier
            .fillMaxSize().background(Color(0xFFF5F5F5)),contentAlignment = Alignment.Center
    ){
        //content
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f).padding(24.dp), horizontalAlignment=Alignment.CenterHorizontally
        ) {


            //title
            Text(
                text = "treasure hunt",fontSize = 32.sp,modifier = Modifier.padding(bottom = 24.dp)
            )

            // perm header
            Text(
                text = "required perms",fontSize=24.sp,modifier=Modifier.padding(bottom = 16.dp)
            )

            // perm descrip.
            Text(
                text = "needs access to your location to determine if you've found the treasure. " +
                        " location is only used while the app is running.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,modifier = Modifier.padding(bottom = 24.dp),color = Color.Gray
            )

            // permission label
            Text(
                text = "Location Permission",
                fontSize = 16.sp,modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp)
            )

            // req 1
            Text(
                text = "Required to track your location",
                fontSize = 12.sp,modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp),
                color = Color.Gray
            )

            //req2
            Text(
                text = "verify when you reach each location",
                fontSize = 12.sp,modifier = Modifier.align(Alignment.Start).padding(bottom = 16.dp),
                color = Color.Gray
            )

            //cont
            Button(
                onClick = onPermissionGranted,modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
            ){
                Text("Grant and Cont.")
            }
        }
    }
}