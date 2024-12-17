package com.example.testexam.view.screens.card

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Popup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testexam.domain.Constants
import com.example.testexam.model.Components
import com.example.testexam.view.navigation.NavRotes
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class CardViewModel: ViewModel() {
    val component = mutableStateOf(Components())

    fun GetComponent(id:Int){
        viewModelScope.launch {
            try{
                val result = Constants.supabase.from("Components").select { filter { eq("id", id) } }.decodeSingle<Components>()
                component.value = result
            }catch (e:Exception){

            }
        }
    }
    fun GoBack(navHostController: NavHostController){
        navHostController.navigate(NavRotes.HOME){
            popUpTo(NavRotes.CARD){
                inclusive = true
            }
        }
    }
}