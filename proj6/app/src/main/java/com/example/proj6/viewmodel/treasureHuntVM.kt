package com.example.proj6.viewmodel

//in charge of the treasure hunt state, clue prog, loc track, timer, and user prog

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proj6.data.clueData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class treasureHuntVM:ViewModel(){
    //will utilize lateint as i while initi. later
    private lateinit var fusedLocClient: FusedLocationProviderClient
    //var for perm
    var locPermGranted = false
    //state
        //clue
    private val _currClueIndex = MutableStateFlow(0)
    val currClueIndex: StateFlow<Int>= _currClueIndex
                //user loc
    private val _userLoc = MutableStateFlow<Location?>(null)
    val userLoc: StateFlow<Location?>= _userLoc
                //elap time
    private val _elapSec = MutableStateFlow(0)
    val elapSec: StateFlow<Int>= _elapSec
                //starthunt
    private val _huntStart = MutableStateFlow(false)
    val huntStart: StateFlow<Boolean>= _huntStart
            //hunt done
    private val _huntDone = MutableStateFlow(false)
    val huntDone: StateFlow<Boolean>= _huntDone
            //hint
    private val _showHint = MutableStateFlow(false)
    val showHint: StateFlow<Boolean>= _showHint
            //wrong loc
    private val _wrongLocation = MutableStateFlow(false)
    val wrongLocation: StateFlow<Boolean>= _wrongLocation
            //curr dist
    private val _currDist = MutableStateFlow(0.0)
    val currDist: StateFlow<Double>= _currDist

    //clues

    private val clues = listOf(
        //had ai help format this and write up clue about locations in San jose
        clueData(
            id = 0,
            clue = "Find where the Sharks live and the ice is always cold",
            hint = "It's a sports arena in downtown San Jose",
            targLat = 37.3382,
            targetLon = -121.8931,
            info = "SAP Center is home to the San Jose Sharks NHL team. It opened in 1993 and hosts hockey games, concerts, and other events throughout the year.",
            geoRad = 30.0
        ),
        clueData(
            id = 1,
            clue = "Find the Egyptian pyramid and ancient mysteries in the middle of California",
            hint = "It's a unique museum with a pyramid-shaped building",
            targLat = 37.3368,
            targetLon = -121.8835,
            info = "The Rosicrucian Museum houses one of the largest collections of Egyptian artifacts in the western United States. The building itself is designed to resemble an Egyptian temple with a distinctive pyramid structure.",
            geoRad = 30.0
        )
    )
    //cliend

    fun setFusedLocClient(client:FusedLocationProviderClient){
        fusedLocClient=client
    }
    //loc perm
    fun setLocPermGranted(granted:Boolean){
        locPermGranted=granted
    }
    //start

    fun startHunt(){
        _huntStart.value=true
        _currClueIndex.value = 0
        _elapSec.value = 0
        _huntDone.value = false
        startTimer()
        startLocUpdates()
    }

    private fun startLocUpdates() {
        if(!locPermGranted){
            return
        }
        viewModelScope.launch{
            try{
                fusedLocClient.lastLocation.addOnSuccessListener{loc: Location?->
                    if(loc!=null){
                        _userLoc.value = loc
                    }
                }
            }
            catch(ex: Exception){
                ex.printStackTrace()
            }
        }
    }

    private fun startTimer() {
        viewModelScope.launch{
            var sec = 0
            while(_huntStart.value && !_huntDone.value){
                kotlinx.coroutines.delay(1000)
                sec++
                _elapSec.value = sec
            }
        }

    }
    fun quitHunt(){
        _huntStart.value =false
        _huntDone.value= true
        _currClueIndex.value = 0
        _elapSec.value = 0
    }
    fun getCurrClue():clueData?{
        if(_currClueIndex.value<clues.size){
           return clues[_currClueIndex.value]
        }
        else{
            return null
        }
    }

    fun showHint(){
        _showHint.value=true
    }
    fun dismissHint(){
        _showHint.value = false
    }
    fun dismissWrongLoc(){
        _wrongLocation.value = false
    }
    fun checkFoundLoc(){
        val currClue = getCurrClue()?:return
        val loc = _userLoc.value?:return

        //dist calc
        val dist = calcDist(
            loc.latitude,loc.longitude,currClue.targLat,currClue.targetLon
        )
        _currDist.value = dist
        //check rad
        if(dist<=currClue.geoRad) {
            if (_currClueIndex.value >= clues.size - 1) {
                _huntDone.value = true
            } else {
                _currClueIndex.value += 1
            }
        }
            else{
                _wrongLocation.value=true
            }
    }

    fun continueToNextClue(){
    if(_currClueIndex.value<clues.size-1){
        _currClueIndex.value +=1
    }
    }

    fun updateLoc(){
        if(!locPermGranted) return

        viewModelScope.launch{

            try{
                fusedLocClient.getCurrentLocation(

                    Priority.PRIORITY_HIGH_ACCURACY, null
                ).addOnSuccessListener { loc: Location? ->

                    if (loc != null) {
                        _userLoc.value = loc
                    }

                }
            }
            catch (ex: Exception){
                ex.printStackTrace()
            }
        }
    }


    private fun calcDist(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
      //used ai to help me with the math for the calc func
        val earthRadiusKm = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)

        val dLon = Math.toRadians(lon2 - lon1)

        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        val distanceKm = earthRadiusKm * c
        return distanceKm * 1000
    }
    fun isLastClue():Boolean{
        return _currClueIndex.value>=clues.size-1
    }
    fun getHuntProgress():Pair<Int,Int>{
        return Pair(_currClueIndex.value+1,clues.size)
    }


}