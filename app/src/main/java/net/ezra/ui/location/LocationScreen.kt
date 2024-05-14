package net.ezra.ui.location

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LocationScreen(navController: NavHostController){

}




@Preview(showBackground = true)
@Composable
fun PreviewLight(){
    LocationScreen(rememberNavController())
}
