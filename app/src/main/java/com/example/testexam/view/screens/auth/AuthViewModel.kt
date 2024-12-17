package com.example.testexam.view.screens.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testexam.domain.Constants
import com.example.testexam.model.Components
import com.example.testexam.view.navigation.NavRotes
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class AuthViewModel :ViewModel() {
    var emailU = mutableStateOf("")
    var passwordU = mutableStateOf("")

    fun Auth(navHostController: NavHostController){
        viewModelScope.launch {
            try{
                val result = Constants.supabase.auth.signInWith(Email){
                    this.email = emailU.value
                    this.password = passwordU.value
                }
                Log.d("auth", "Success")
                navHostController.navigate(NavRotes.HOME){
                    popUpTo(NavRotes.AUTH){
                        inclusive = true
                    }
                }
            }
            catch (e:Exception){
                Log.d("auth", e.message.toString())
            }
        }

    }

}