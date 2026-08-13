package com.example.proj6

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proj6.ui.navigation.TreasureHuntNav
import com.example.proj6.ui.theme.TreasureHuntTheme
import com.example.proj6.viewmodel.treasureHuntVM
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

//apps entry point, init. loc service, handles the perms, and composes UI

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocClient=LocationServices.getFusedLocationProviderClient(this)
        setContent {
            TreasureHuntTheme {
                val viewModel:treasureHuntVM =viewModel()
                var fineLocGranted =remember{mutableStateOf(false)}


                val locPermRequest =rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission()
                ) { isGranted: Boolean ->
                    fineLocGranted.value = isGranted
                    viewModel.setLocPermGranted(isGranted)
                }

                LaunchedEffect(Unit) {
                    when {
                        ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED -> {
                        fineLocGranted.value = true
                        viewModel.setLocPermGranted(true)}
                        else->{
                            locPermRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                        }
                    }
                }
                viewModel.setFusedLocClient(fusedLocClient)
                TreasureHuntNav(

                    viewModel = viewModel,
                    locPermGranted = fineLocGranted.value
                )
            }
        }
    }
}