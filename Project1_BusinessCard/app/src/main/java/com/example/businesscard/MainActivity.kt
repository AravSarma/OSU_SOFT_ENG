/* Assignment 1 Business Card

Arav Sarma sarmaar@oregonstate.edu
cs 492/ Oregon State University
*/


package com.example.businesscard
//imports with comments found with help of ai


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
// Image: composable that displays an image (used for the Android logo)
import androidx.compose.foundation.Image
// wildcard import: brings in Column, fillMaxSize(), Arrangement, size(), padding()
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
// Alignment: used to center content horizontally
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//Color: used for background and text colors
import androidx.compose.ui.graphics.Color
// painterResource: loads the logo image from drawable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
// dp: sizing unit
import androidx.compose.ui.unit.dp
// sp: font sizing unit
import androidx.compose.ui.unit.sp

import androidx.compose.ui.tooling.preview.Preview

import com.example.businesscard.ui.theme.BusinessCardAravSarmaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardAravSarmaTheme {
                BusinessCard()
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    //surface fiulls entire screen
    Surface(
        //fill entire screen
        //color from my website, aravsarma.com

        modifier = Modifier.fillMaxSize(),color = Color(0xFFD8D5DA)

    ){
        //arrange vert
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //show icon
            Image(
                painter = painterResource(id = R.drawable.android_logo), contentDescription = "AndroidLogo",modifier = Modifier.size(120.dp)
            )
            //show text
            Text(
                text = "CS 492 Student Extraordinaire",color = Color(0xFF1E1B2F),fontSize=28.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
            Text(
                text = "Arav Sarma", color = Color(0xFF1E1B2F), fontSize = 28.sp, fontWeight = FontWeight.SemiBold
            )
            //create spacer, saw issue in preview
            Spacer(modifier= Modifier.height(48.dp))
            //contact info, site, linekedin etc
            //colors are from my website straight , aravsarma.com
            Text(text = "408-628-8445", color = Color(0xFF1E1B2F))
            Text(text = "sarmaar@oregonstate.edu", color = Color(0xFF1E1B2F))
            Text(text = "aravsarma.com", color = Color(0xFF1E1B2F))
            Text(text = "linkedin.com/in/arav-sarma", color = Color(0xFF1E1B2F))



        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardAravSarmaTheme {
        BusinessCard()
    }
}