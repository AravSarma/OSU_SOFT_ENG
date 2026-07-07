package com.example.project2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
//import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.project2.ui.theme.Project2Theme

//row import
import androidx.compose.foundation.layout.Row
//dp import for scaling
import androidx.compose.ui.unit.dp
//surface import for background container
import androidx.compose.material3.Surface
//spacer import to render empty space
import androidx.compose.foundation.layout.Spacer

//for display
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project2Theme {
                    Surface(modifier = Modifier.fillMaxSize()){
                        ArtSpace()
                    }
                }
            }
        }
    }

@Composable
fun ArtSpace() {
    //count var start at 3
    var currCount by remember {mutableStateOf(3)}

    //col
    Column(modifier =Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        //image
        Surface(
            modifier = Modifier.weight(1f).fillMaxWidth()
                .shadow(elevation = 0.dp, shape = RoundedCornerShape(6.dp)), color = Color.Blue
        )
        {
            Image(
                painter = painterResource(id = R.drawable.android_logo),
                contentDescription = "andr logo",
                modifier = Modifier.padding(12.dp).fillMaxSize()

            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "curr count:$currCount", fontSize = 25.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))
        Row{
            Text(
                text = "Arav Sarma",fontSize = 15.sp,fontWeight = FontWeight.Bold
            )
            Text(
                text = "CS492",fontSize = 15.sp
            )
        }
        //button
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly
        ){
        Button(onClick = {
            currCount = if (currCount - 1 <1) 5 else currCount -1
        }) {
            Text("previous")
        }
        Button(onClick = {
            currCount = if (currCount + 1 >5) 1 else currCount +1
        }) {
            Text("next")
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    Project2Theme {
        ArtSpace()
    }
}