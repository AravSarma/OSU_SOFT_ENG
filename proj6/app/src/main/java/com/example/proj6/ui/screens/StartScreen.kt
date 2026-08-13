package com.example.proj6.ui.screens
//start screen,displays title, rules, and button to start
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//used ai to help with padding and formatting  and color, and for rules to save time
//on repetition

@Composable
fun StartScreen(onStartClick: () -> Unit) {
// Main container
Box(
    modifier = Modifier
        .fillMaxSize().background(Color(0xFFF5F5F5))
){

    //contnet
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // title
        Text(
            text = "Treasure Hunt",fontSize = 40.sp,modifier=Modifier.padding(top=32.dp, bottom = 16.dp)
        )
        //subtitle
        Text(
            text = "follow  the clues. Find  treasure.",
            fontSize = 16.sp,color =Color.Gray,modifier =Modifier.padding(bottom=32.dp),textAlign = TextAlign.Center
        )

        //scroll rule area
        Column(
            modifier = Modifier
                .fillMaxWidth().weight(1f).verticalScroll(rememberScrollState()).padding(bottom = 24.dp)
        ){
            //rules H
            Text(
                text = "how to play ",fontSize = 20.sp,modifier =Modifier.padding(bottom = 16.dp)
            )
            //USED AI ASSISTANCE HERE TO SAVE TIME FOR RULE, INSTEAD OF COPY PASTE
            // Rule 1
            RuleItem(
                "1. Read the clue carefully. It describes a real-world location."
            )
            // Rule 2
            RuleItem(
                "2. Use the hint button if you get stuck."
            )
            // Rule 3
            RuleItem(
                "3. When you think you've found the location, press 'Found It!'"
            )
            // Rule 4
            RuleItem(
                "4. The app uses GPS to verify you're at the correct location (within ~30 meters)."
            )
            // Rule 5
            RuleItem(
                "5. Once verified, you'll receive the next clue."
            )
            // Rule 6
            RuleItem(
                "6. Solve all clues to complete the treasure hunt and see your total time."
            )
            // Rule 7
            RuleItem(
                "7. You can quit at any time and return to this screen."
            )


            Text(
                text = "Tips",fontSize = 18.sp,modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
            )


            //used ai here to save time on tips, similar to rule section
            // Tip 1
            RuleItem(
                "• GPS accuracy is typically ±5 meters in open areas"
            )
            // Tip 2
            RuleItem(
                "• Trees, buildings, and other obstacles can reduce accuracy"
            )
            // Tip 3
            RuleItem(
                "• Be patient - GPS may take a few seconds to locate you"
            )
        }

        //start
        Button(
            onClick = onStartClick,modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        {
            Text("Start the hunt")
        }
    }
}
}

// Helper composable for rule items
@Composable
fun RuleItem(text: String){
Text(
    text = text,fontSize = 14.sp,
    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),textAlign = TextAlign.Left,color = Color.DarkGray
)
}