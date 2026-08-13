package com.example.proj5.ui

//last screen, displayes the name and addy at the top
//if there are none, display text box and save button
//once its there it displays


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedButton

//sho rec name & addy, save

@Composable

fun RecommendationDetailScreen(
    viewModel: CityViewModel, recommendationId:String, onBackClick:()-> Unit
){
    //get specific rec
    val recommendation = viewModel.getRecommendation(recommendationId)
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier=Modifier.fillMaxSize().padding(15.dp)) {
        //button originally was to highup this moves it down ,for future reference
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            OutlinedButton(onClick = onBackClick) {
                Text("back")
            }
        }
        //err check
        if(recommendation ==null){
            Text("recommendation isnt found")
            return@Column
        }
        Text(
            text=recommendation.name,style=MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold
        )
        if(recommendation.address!=null){
            Spacer(modifier=Modifier.height(5.dp))
            Text(
                text=recommendation.address,style= MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        //if there are no details show input, if they exist show the text
        if(recommendation.details ==null){
            OutlinedTextField(
                value=uiState.newDetailInput,onValueChange=viewModel::InputChanged,label={Text("enter details")},modifier=Modifier.fillMaxWidth()
            )
            Spacer(modifier=Modifier.height(10.dp))
            //button
            Button(
                onClick={viewModel.saveDetail(recommendationId)},modifier=Modifier.fillMaxWidth()
            ){
                Text("save")
            }
        }
            else{
                Text(recommendation.details,style=MaterialTheme.typography.bodyLarge)
            }


    }
}