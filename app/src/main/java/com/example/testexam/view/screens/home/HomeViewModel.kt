package com.example.testexam.view.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testexam.domain.Constants
import com.example.testexam.model.Category
import com.example.testexam.model.Components
import com.example.testexam.model.Maker
import com.example.testexam.model.MakerComponents
import com.example.testexam.view.navigation.NavRotes
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    var componentsList = mutableStateOf<List<Components>>(emptyList())
    var search = mutableStateOf("")
    var categoryList = mutableStateOf<List<Category>>(emptyList())
    var selectCategory = mutableStateOf(0)
    var makerList = mutableStateOf<List<MakerComponents>>(emptyList())
    var selectMaker = mutableStateOf(0)
    var makeNameList = mutableStateOf<List<Maker>>(emptyList())
    fun GetCategory(){
        viewModelScope.launch {
            try{
                categoryList.value = Constants.supabase.from("Category").select().decodeList<Category>()
            }
            catch (e:Exception){

            }
        }
    }
    fun GetMaker(){
        viewModelScope.launch {
            try{
                makeNameList.value = Constants.supabase.from("Maker").select().decodeList<Maker>()
                makerList.value = Constants.supabase.from("MakerComponents").select().decodeList<MakerComponents>()
                Log.d("home-make-ok", makerList.value.size.toString())
            }
            catch (e:Exception){
                Log.d("home-maker", e.message.toString())
            }
        }
    }
    fun GetComponent(){

        viewModelScope.launch {
            try{
                val result = Constants.supabase.from("Components").select(){
                    filter {
                        if(search.value != ""){
                            like("name","${search.value}%")
                        }
                        if(selectCategory.value!= 0){
                            Log.d("home-id", selectCategory.value.toString())
                            eq("category",selectCategory.value)
                        }
                        if(selectMaker.value!=0 ){
                            val responce = Constants.supabase.from("MakerComponents").select{filter { eq("id_maker",selectMaker.value) }}.decodeSingle<MakerComponents>()
                            eq("id", responce.id_component)
                        }
                    }
                }.decodeList<Components>()
                componentsList.value = result

            }catch (e:Exception){
                Log.d("home", e.message.toString())
            }
        }
    }
    fun GetCard(navHostController: NavHostController, id:Int){
        navHostController.navigate(NavRotes.CARD + "/${id}"){
            popUpTo(NavRotes.HOME){
                inclusive = true
            }
        }
    }
}