package com.example.proj6.ui.navigation

//this file will define nav routes for nvhost
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proj6.ui.screens.ClueScreen
import com.example.proj6.ui.screens.CluesSolvedScreen
import com.example.proj6.ui.screens.PermissionsScreen
import com.example.proj6.ui.screens.StartScreen
import com.example.proj6.ui.screens.TreasureCompletedScreen
import com.example.proj6.viewmodel.treasureHuntVM

//def nav route consts
object TreasureHuntRoutes{
    const val PERMS = "permissions"
    const val START = "start"
    const val CLUE = "clue"
    const val CLUE_SOLVE = "clueSolve"
    const val COMP = "complete"
}

//nav comp. to handle transitions
@Composable
fun TreasureHuntNav(
    viewModel: treasureHuntVM,locPermGranted:Boolean, navController:NavHostController = rememberNavController()
) {
    // NavHost determines starting destination based on permission status
NavHost(
    navController = navController,
    startDestination = if (locPermGranted){


        TreasureHuntRoutes.START
    }
    else{
        TreasureHuntRoutes.PERMS

    }
)
{
    //grants location permission
    composable(TreasureHuntRoutes.PERMS){

        PermissionsScreen(
            onPermissionGranted = {

                navController.navigate(TreasureHuntRoutes.START){
                    popUpTo(TreasureHuntRoutes.PERMS) {inclusive=true}

                }
            }

        )
    }


    // Start;  title, rules,start button
    composable(TreasureHuntRoutes.START){
        StartScreen(

            onStartClick={
                viewModel.startHunt()
                navController.navigate(TreasureHuntRoutes.CLUE)
            })
    }

    //game screen with timer and location checking
    composable(TreasureHuntRoutes.CLUE){

        ClueScreen(
            viewModel = viewModel,onClueFound={
                if(viewModel.huntDone.value){
                    navController.navigate(TreasureHuntRoutes.COMP)
                    {
                        popUpTo(TreasureHuntRoutes.CLUE){inclusive=true}
                    }
                }
                else{
                    navController.navigate(TreasureHuntRoutes.CLUE_SOLVE)
                }
            },
            onQuit={
                viewModel.quitHunt()
                navController.navigate(TreasureHuntRoutes.START){
                    popUpTo(TreasureHuntRoutes.CLUE){inclusive=true}
                }

            }

        )

    }

    // solved scree , loc  info after solving clue
    composable(TreasureHuntRoutes.CLUE_SOLVE){
        CluesSolvedScreen(

            viewModel = viewModel,onContinue={
                navController.navigate(TreasureHuntRoutes.CLUE){
                    popUpTo(TreasureHuntRoutes.CLUE_SOLVE) {inclusive = true}}
            })
    }

    // show  completion and  time
    composable(TreasureHuntRoutes.COMP){
        TreasureCompletedScreen(
            viewModel = viewModel,onHome= {
                navController.navigate(TreasureHuntRoutes.START) {
                    popUpTo(TreasureHuntRoutes.COMP) { inclusive = true }
                }
            })
    }
}
}

