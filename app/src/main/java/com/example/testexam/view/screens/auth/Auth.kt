package com.example.testexam.view.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun Auth (navHostController: NavHostController) {
    val vm = viewModel{AuthViewModel()}

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            vm.emailU.value,
            {vm.emailU.value = it},
            textStyle = TextStyle(fontSize =  30.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor= Color(0xff16a085), // цвет при получении фокуса
                unfocusedBorderColor = Color(0xffcccccc)  // цвет при отсутствии фокуса
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            vm.passwordU.value,
            {vm.passwordU.value = it},
            textStyle = TextStyle(fontSize =  30.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor= Color(0xff16a085), // цвет при получении фокуса
                unfocusedBorderColor = Color(0xffcccccc)  // цвет при отсутствии фокуса
            )
        )
        Button(onClick = {vm.Auth(navHostController)},
            shape = RoundedCornerShape(15.dp),  // округлая кнопка
            modifier = Modifier.padding(10.dp)
        ){ Text("Войти", fontSize = 28.sp) }
    }


}