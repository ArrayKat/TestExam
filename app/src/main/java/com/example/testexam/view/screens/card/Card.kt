package com.example.testexam.view.screens.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun Card (navHostController: NavHostController, idConponent: Int){
    val vm = viewModel{CardViewModel()}
    LaunchedEffect(Unit) {
        vm.GetComponent(idConponent)
    }
    Column {
        Button(onClick = {vm.GoBack(navHostController)},
            shape = RoundedCornerShape(15.dp),  // округлая кнопка
            modifier = Modifier.padding(10.dp)
        ){ Text("Войти", fontSize = 28.sp) }
        val imageState = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(vm.component.value.image_url)
                .size(Size.ORIGINAL)
                .build()
        ).state
        if(imageState is AsyncImagePainter.State.Success){
            Image(painter = imageState.painter, contentDescription = "")
        }
        if(imageState is AsyncImagePainter.State.Error){
            CircularProgressIndicator()
        }
        Text(vm.component.value.name)
    }
}