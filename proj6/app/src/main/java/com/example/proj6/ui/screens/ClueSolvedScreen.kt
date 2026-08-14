package com.example.proj6.ui.screens

//disp for info about solved clue loc and goes to next one

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proj6.viewmodel.treasureHuntVM

@Composable
fun CluesSolvedScreen(
    viewModel: treasureHuntVM,onContinue:()-> Unit
){
    //curr clue and elap time
    val currClue = viewModel.getCurrClue()
    val elapSec = viewModel.elapSec.collectAsState()

    //exit
    if(currClue==null){
        return
    }

    //clue prog
    val(currClueNum,totalClues)=viewModel.getHuntProgress()

    //used some ai to help with formating and color
    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))
    ){
        //conten
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally
        ){
            //solved msg
            Text(
                text = "clue solved",fontSize = 32.sp,modifier = Modifier.padding(bottom = 8.dp)
            )


            //prog ind.
            Text(
                text = "Clue $currClueNum of $totalClues",fontSize=14.sp,color=Color.Gray, modifier = Modifier.padding(bottom = 16.dp)
            )


            //elap time
            Text(
                text = formatTime(elapSec.value),fontSize=24.sp,color = Color.Gray,
                modifier = Modifier.padding(bottom = 24.dp)
            )


            //scroll box for info
            Column(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f).verticalScroll(rememberScrollState()).background(Color.White).padding(16.dp).padding(bottom = 24.dp)
            ) {
                //loc info text
                Text(
                    text = currClue.info,fontSize = 14.sp,textAlign = TextAlign.Left,modifier=Modifier.padding(horizontal = 8.dp),color = Color.DarkGray
                )
            }

            //cont for nexyt clue
            Button(
                onClick = {
                    viewModel.continueToNextClue()
                    onContinue()
                },


                modifier = Modifier.fillMaxWidth()
            )
            {
                Text("Next Clue")
            }
        }
    }
}