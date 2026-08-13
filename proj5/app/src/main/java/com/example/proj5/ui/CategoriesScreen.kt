package com.example.proj5.ui

//similar to mine from proj4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proj5.model.Category


//used alot from p4
//this is s1, it shows the city name and the list of cats,
//user can add new cat and delete
@Composable
fun CategoriesScreen(
    viewModel: CityViewModel,onCatClick:(Category)->Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    CategoriesContent(
        //similar to proj4
        cityName = uiState.cityName,
        categories = uiState.category,
        newCategoryInput = uiState.newCategory,
        errorMessage = uiState.errorMsg,
        onInputChanged = viewModel::onNewCategoryInputChanged,
        onAddClicked = viewModel::addCategory,
        onDeleteClicked = viewModel::deleteCat,
        onCatClick = onCatClick
    )
}

@Composable
private fun CategoriesContent(
    cityName: String,
    categories: List<Category>,
    newCategoryInput: String,
    errorMessage: String?,
    onInputChanged: (String) -> Unit,
    onAddClicked: () -> Unit,
    onDeleteClicked:(Category)->Unit,
    onCatClick:(Category)->Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.fillMaxSize().padding(15.dp)) {
        Text(
            text = cityName,style = MaterialTheme.typography.headlineMedium,fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            modifier= Modifier.fillMaxWidth().weight(1f)
        ) {
            items(categories,key={it.id}) { category ->
                CategoryRow(
                    //add onlick,
                    category = category, onDeleteClicked = { onDeleteClicked(category) },onClick={onCatClick(category)}
                )
                //divider
                //this part is differnt from proj 4, new version from google
                HorizontalDivider()
            }
        }
        if(errorMessage!=null){
            Text(
                text = errorMessage,color= MaterialTheme.colorScheme.error,style=MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value=newCategoryInput, onValueChange = onInputChanged, label={Text("new category")},modifier= Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onAddClicked) {
                Text("add")
            }
        }
    }
}
@Composable

private fun CategoryRow(
    category:Category,onDeleteClicked: () -> Unit,onClick:()->Unit,modifier: Modifier= Modifier
){
    Row(
        modifier=modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ){
        /* version from proj4, change so it can trigger a nav to respective page
        Text(
            text = category.name,style=MaterialTheme.typography.bodyMedium
        )
        */
        Text(
            text=category.name, style = MaterialTheme.typography.bodyMedium,modifier=Modifier.weight(1f).clickable(onClick = onClick)
        )

        Button(onClick = onDeleteClicked) {
            Text("X")
        }
    }
}
