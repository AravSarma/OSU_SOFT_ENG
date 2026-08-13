package com.example.project4_part1.ui

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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.project4_part1.model.Category
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel


//use @compasable to tell compuiler to build ui
//this file is what is responsible for displaying the content
@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    CategoriesContent(
        //used ai to format these instead of one line
        cityName = uiState.cityName,
        categories = uiState.category,
        newCategoryInput = uiState.newCategory,
        errorMessage = uiState.errorMsg,
        onInputChanged = viewModel::onNewCategoryInputChanged,
        onAddClicked = viewModel::addCategory,
        onDeleteClicked = viewModel::deleteCat
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
    onDeleteClicked: (Category) -> Unit,
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
                    category = category, onDeleteClicked = { onDeleteClicked(category) }
                )
                //divider
                Divider()
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
    category:Category,onDeleteClicked: () -> Unit,modifier: Modifier= Modifier
){
    Row(
        modifier=modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = category.name,style=MaterialTheme.typography.bodyMedium
        )
        Button(onClick = onDeleteClicked) {
                Text("X")
            }
        }
    }

