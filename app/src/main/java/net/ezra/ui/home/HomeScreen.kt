package net.ezra.ui.home






import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import kotlinx.coroutines.launch
import net.ezra.R
import net.ezra.navigation.ROUTE_CATEGORY

import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_PROFILES
import net.ezra.navigation.ROUTE_REGISTRATION

data class NavigationItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)

val item = listOf(
    NavigationItem(
        title = "All",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,

        ),
    NavigationItem(
        title = "Urgent",
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info,
        badgeCount = 45,


        ),
    NavigationItem(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,

        )
)



data class  BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,



    )



    val items= listOf(
    BottomNavigationItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,


    ),

    BottomNavigationItem(
        title = "Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = false,



        ),

    BottomNavigationItem(
        title = "Categories",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        hasNews = false,
        badgeCount = 9,
    ),

    BottomNavigationItem(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = false,


    ),

    )









data class Screen(val title: String, val icon: Int)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    var isDrawerOpen by remember {
        mutableStateOf(false)
    }


    val callLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { _ ->
        }
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    var selectedSecondListIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(


            ) {
                Spacer(modifier = Modifier.height(16.dp))
                item.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.title)
                        },
                        selected = index == selectedSecondListIndex ,
                        onClick = {
                            selectedSecondListIndex = index
                            scope.launch {
                                drawerState.close()

                            }

                        },
                        icon = {
                            Icon(
                                imageVector =
                                if( index == selectedSecondListIndex) {
                                    item.selectedIcon
                                }else item.unselectedIcon,
                                contentDescription =item.title ,
                                tint = if (index == selectedItemIndex) {
                                    Color((0xff87CEEB))
                                } else Color.Unspecified
                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }


                        },

                        colors = NavigationDrawerItemDefaults.colors(Color(0xff87CEEB)),
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
//



                    )

                }

            }

        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.apen))
                    },
                    navigationIcon = @Composable {
                        if (!isDrawerOpen) {
                            IconButton(onClick = { isDrawerOpen = true }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.White
                                )
                            }
                        }
                    },

                    actions = {
                        IconButton(onClick = {
                            navController.navigate(ROUTE_LOGIN) {
                                popUpTo(ROUTE_HOME) { inclusive = true }
                            }

                        }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xff87CEEB),
                        titleContentColor = Color.White,

                        )

                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = Color(0xff87CEEB),
                    contentColor = Color.White,

                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 50.dp)
                        .clip(RoundedCornerShape(30.dp))
                        ,

                ) {
                    items.forEachIndexed { index, item ->
                       NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index

                            },
                            label = {
                                Text(text = item.title)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(
                                                    text = item.badgeCount.toString()
                                                )
                                            }
                                        } else if (item.hasNews) {
                                            androidx.compose.material.Badge()
                                        }

                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,

                                        contentDescription = item.title,
                                        tint = Color.White

                                    )


                                }

                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))

                        )

                    }
                }


            },
            content = @Composable {
                LazyColumn(
                    modifier = Modifier
                        .background(Color(0xffe4e3e3))
                        .fillMaxSize()

                ) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                            Image(
                                painter = painterResource(id = R.drawable.africa),
                                contentDescription ="",
                                modifier =Modifier
                                    .clip(RoundedCornerShape(20.dp)))

                        }

                        Column(
                            modifier = Modifier

                                .background(Color.White),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {


                            Text(
                                text = "Welcome to Yaya Kenya",
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .clickable {

                                        val intent = Intent(Intent.ACTION_DIAL)
                                        intent.data = Uri.parse("YAYA KENYA")

                                        callLauncher.launch(intent)
                                    }
                            )

                            Text(
                                text = "Powered by Reeves Tech Solutions",
                                fontSize = 20.sp,
                            )
                            Spacer(modifier = Modifier.height(15.dp))




                            Card(
                               onClick = {
                                         navController.navigate(ROUTE_CATEGORY)
                               },
                                elevation = CardDefaults.cardElevation(20.dp),
                                colors = CardDefaults.cardColors(Color(0xff87CEEB)),
                                modifier = Modifier
                                    .padding(start = 80.dp, end = 50.dp,)
                                    .size(width = 300.dp, height = 100.dp)
                                    .clip(RoundedCornerShape(40.dp))
                            ) {
                                Text(
                                    text = "Start your Journey",
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 100.dp, top = 30.dp, end = 50.dp,)

                                    )

                            }


                            Spacer(modifier = Modifier.height(15.dp))

                        }



                    }




                }

            },
        )

    }
}

@Preview
@Composable
fun PreviewLight(){
    HomeScreen(rememberNavController())
}









