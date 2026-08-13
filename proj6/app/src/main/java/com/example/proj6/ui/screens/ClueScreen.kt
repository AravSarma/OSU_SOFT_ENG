package com.example.proj6.ui.screens
//main screen. displays clue, timer, hint, and found, checks loc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
import kotlin.math.roundToInt

@Composable

fun ClueScreen(
    viewModel: treasureHuntVM,onClueFound:()->Unit, onQuit:()-> Unit
){
    val currClue = viewModel.getCurrClue()
    val elapSec = viewModel.elapSec.collectAsState()
    val showHint = viewModel.showHint.collectAsState()
    val showWrongLoc = viewModel.wrongLocation.collectAsState()
    val huntDone = viewModel.huntDone.collectAsState()
    val currDist = viewModel.currDist.collectAsState()

    //check done
    if(huntDone.value){
        onClueFound()
        return
    }
    if(currClue==null){
        return
    }
    val(currClueNum,totalClues) = viewModel.getHuntProgress()
    //used ai to help with color and formattring

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))
    ){

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),horizontalAlignment=Alignment.CenterHorizontally
        ){Text(
                text ="Clue $currClueNum of $totalClues",fontSize = 14.sp,color = Color.Gray,
                modifier =Modifier.padding(bottom = 8.dp)
            )
            Text(
                text =formatTime(elapSec.value),fontSize = 32.sp,modifier =Modifier.padding(bottom = 24.dp)
            )


            Box(
                modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp).padding(bottom = 24.dp)
            ){


                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "The Clue",fontSize=16.sp,color=Color.Gray,modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Text(
                        text =currClue.clue,fontSize = 18.sp,textAlign = TextAlign.Center,modifier = Modifier.padding(horizontal = 8.dp),color=Color.DarkGray

                    )
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(bottom=16.dp),horizontalArrangement=androidx.compose.foundation.layout.SpaceBetween
            ){
                OutlinedButton(
                    onClick={viewModel.showHint()},modifier = Modifier.weight(1f)
                ){
                    Text("hint")
                }



                OutlinedButton(
                    onClick ={viewModel.updateLoc()},modifier=Modifier.weight(1f).padding(start = 8.dp)
                ) {
                    Text("update gps")
                }
            }


            Button(
                onClick ={viewModel.checkFoundLoc()},modifier=Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("found it")
            }


            OutlinedButton(
                onClick =onQuit,modifier =Modifier.fillMaxWidth()
            ) {
                Text("quit")
            }

        }

    }
//hint
    if (showHint.value) {
        AlertDialog(
            onDismissRequest = {viewModel.dismissHint()},
            title = {Text("Hint")},text = {Text(currClue.hint)},
            confirmButton ={

                Button(onClick = {viewModel.dismissHint()}) {
                    Text("ok")
                }
            }

        )

    }

//wring loc
    if (showWrongLoc.value) {
        val distMeters=currDist.value.roundToInt()

        val distText = if (distMeters > 1000){
            String.format("%.2f km", distMeters / 1000.0)
        }
        else
        {

            "$distMeters meters"
        }

        AlertDialog(
            onDismissRequest = { viewModel.dismissWrongLoc() },
            title = {Text("Not quite:")},
            text = {

                Text(
                    "You're  $distText away from the location " +
                            "Keep looking"
                )

            },
            confirmButton ={
                Button(onClick = {viewModel.dismissWrongLoc()}) {
                    Text("Try Again")
                }

            }

        )

    }
}
//used ai to help with math
fun formatTime(seconds: Int): String {
    val hours = seconds / 3600

    val minutes = (seconds % 3600) / 60

    val secs = seconds % 60

    return String.format("%02d:%02d:%02d", hours, minutes, secs)
}