package com.example.proj5.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proj5.model.Recommendation
import androidx.compose.material3.OutlinedButton


//this is s2. shows cat name, and list that scrolls of the cat's recommendations
//below has a text box and save to add new recs
//checks dups and deletes, and changes nav


//need a stateful wrapper to pull cat's data out of shared state
@Composable
fun RecommendationsScreen(
    viewModel:CityViewModel,categoryId:String, onRecommendationClick:(Recommendation)->Unit,onBackClick:()->Unit
){
    val uiState by viewModel.uiState.collectAsState()
    val category = uiState.category.find{it.id==categoryId}
    val recommendations = viewModel.recommendCategory(categoryId)

    RecommendationsContent(
        categoryName=category?.name ?:"Category", recommendations=recommendations, newRecommendationInput = uiState.newRecommendation,
        errorMessage=uiState.recommendationErrorMsg,onInputChanged=viewModel::newRecommendation,onSaveClicked={ viewModel.addRecommendation(categoryId)},
        onDeleteClicked=viewModel::deleteRecommendation, onBackClick = onBackClick, onRecommendationClick=onRecommendationClick
    )
}
@Composable
private fun RecommendationsContent(
    categoryName:String, recommendations:List<Recommendation>, newRecommendationInput:String, errorMessage:String?,
    onInputChanged:(String)->Unit,onSaveClicked:()->Unit,onDeleteClicked:(Recommendation)->Unit,
    onBackClick:()->Unit,modifier:Modifier=Modifier, onRecommendationClick:(Recommendation)->Unit
){
    Column(modifier=modifier.fillMaxSize().padding(15.dp)){
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            OutlinedButton(onClick = onBackClick) {
                Text("back")
            }
        }
        Text(
           text=categoryName,style=MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold
        )
        Spacer(modifier=Modifier.height(16.dp)
        )
        //make scrollable list
        LazyColumn(modifier= Modifier.fillMaxWidth().weight(1f)) {
            //loop key by its id , compose will track
            items(recommendations,key={it.id}){
                recommendation->RecommendationRow(
                    recommendation=recommendation,onDeleteClicked={onDeleteClicked(recommendation)},onClick={onRecommendationClick(recommendation)}

                )
                //divude
                HorizontalDivider()
            }

        }
        //err check
        if(errorMessage!=null){
            Text(
                text = errorMessage,color= MaterialTheme.colorScheme.error,style=MaterialTheme.typography.bodySmall
            )
            Spacer(modifier= Modifier.height(4.dp))
        }

    OutlinedTextField(
        value=newRecommendationInput, onValueChange = onInputChanged,label={Text(
            "new recommendation"
        )},modifier= Modifier.fillMaxWidth()
    )
    Spacer(modifier=Modifier.height(8.dp))
    //button
    Button(onClick = onSaveClicked,modifier= Modifier.fillMaxWidth()){
        Text("save")
    }
        }
}

@Composable
//take one row from rec list
private fun RecommendationRow(
    recommendation:Recommendation,onDeleteClicked:()->Unit,onClick:()->Unit,modifier:Modifier= Modifier
){
    Row(
        modifier=modifier.fillMaxWidth().padding(vertical=10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment= Alignment.CenterVertically
    ){
        Text(
            text=recommendation.name,style=MaterialTheme.typography.bodyMedium,modifier=Modifier.weight(1f).clickable(onClick=onClick)
        )
        Button(onClick=onDeleteClicked){
            Text("x")
        }
    }
}