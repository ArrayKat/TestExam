package com.example.testexam.view.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun Home (navHostController: NavHostController) {
    val vm = viewModel{HomeViewModel()}
    LaunchedEffect(Unit) {
        vm.GetCategory()
        vm.GetComponent()
        vm.GetMaker()
    }

    Column {

        LazyColumn {
            item {
                OutlinedTextField(
                    vm.search.value,
                    {vm.search.value = it},
                    textStyle = TextStyle(fontSize =  30.sp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor= Color(0xff16a085), // цвет при получении фокуса
                        unfocusedBorderColor = Color(0xffcccccc)  // цвет при отсутствии фокуса
                    ),
                    keyboardActions = KeyboardActions(onAny = {vm.GetComponent()}),
                    singleLine = true

                )
            }
            item {
                LazyRow{
                    items(vm.categoryList.value){ category ->
                        Text(text = category.name, modifier = Modifier.clickable {
                            vm.selectCategory.value = category.id
                            vm.GetComponent()
                        })
                    }
                }
            }
            item {
                LazyRow{
                    items(vm.makeNameList.value){ maker ->
                        Text(text = maker.name.toString(), modifier = Modifier.clickable {
                            vm.selectMaker.value = maker.id
                            vm.GetComponent()
                        })
                    }
                }
            }
            items(vm.componentsList.value){ item ->
                Column (modifier = Modifier.clickable { vm.GetCard(navHostController,item.id) }) {
                    val imageState = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.image_url)
                            .size(Size.ORIGINAL)
                            .build()

                    ).state
                    if(imageState is AsyncImagePainter.State.Success){
                        Image(painter = imageState.painter, contentDescription = "")
                    }
                    if(imageState is AsyncImagePainter.State.Error){
                        CircularProgressIndicator()
                    }
                    Text(text = item.name)
                }

            }
        }
    }
}