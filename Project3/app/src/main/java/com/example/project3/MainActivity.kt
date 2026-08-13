package com.example.project3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.project3.data.cdSource
import com.example.project3.model.Course
import com.example.project3.ui.theme.Project3Theme
//surface card other imports
import androidx.compose.material3.Surface
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Project3Theme() {
                Surface(
                    modifier=Modifier.fillMaxSize(),color= MaterialTheme.colorScheme.background
                ){
                    courseLS(courses = cdSource.courses)
                }
            }
        }
    }
}

@Composable
fun courseLS(courses: List<Course>, modifier: Modifier=Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(courses) { course ->courseCard(course = course)}

    }
}

@Composable
fun courseCard(course:Course, modifier: Modifier=Modifier) {
    Card(modifier = modifier.fillMaxWidth()){
        Column(modifier = Modifier.padding(16.dp)){
            Text(
                text= "${course.department} ${course.number}", fontWeight = FontWeight.Bold,style= MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(id = course.title),modifier=Modifier.padding(top=5.dp),style=MaterialTheme.typography.bodyLarge
            )
            Text(
                text=stringResource(id=course.title),modifier=Modifier.padding(top=5.dp),style = MaterialTheme.typography.bodySmall
            )
        }
    }
    }
@Composable
fun CourseListPreview() {
    Project3Theme {
        courseLS(courses = cdSource.courses)
    }
}