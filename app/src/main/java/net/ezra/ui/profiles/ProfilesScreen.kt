package net.ezra.ui.profiles

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
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
import net.ezra.navigation.ROUTE_PROFILES
import net.ezra.ui.dashboard.items
import net.ezra.ui.home.BottomNavigationItem

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
    val badgeCount:Int? = null,

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





@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilesScreen(navController: NavHostController){
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
                net.ezra.ui.home.item.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            androidx.compose.material3.Text(text = item.title)
                        },
                        selected = index == selectedSecondListIndex ,
                        onClick = {
                            selectedSecondListIndex = index
                            scope.launch {
                                drawerState.close()

                            }

                        },
                        icon = {
                            androidx.compose.material3.Icon(
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
                                androidx.compose.material3.Text(text = item.badgeCount.toString())
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
                TopAppBar(
                    title = {
                        Text(
                            text = "Yaya Kenya",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }

                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )

                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(Color(0xff87CEEB)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))

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
//                                 navController.navigate(item.title)

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
                        .background(Color.White)
                        .fillMaxSize()

                ) {
                    item {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                            modifier =Modifier
                                .padding( start = 100.dp, end = 50.dp)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.lady),
                                contentDescription = "",
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop,

                                modifier = Modifier
                                    .size(width = 200.dp, height = 200.dp)
                                    .clip(CircleShape)
                                    .padding(top = 10.dp)

                            )
                            Row {
                                Text(
                                    text = "Arya Stark",
                                    textAlign = TextAlign.Left,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(start = 6.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Row {
                                Text(
                                    text = "25 Years",
                                    textAlign = TextAlign.Left,
                                    fontWeight = FontWeight.Light,
                                    fontSize = 13.sp,
                                    color = Color(0xff87CEEB),
                                    modifier = Modifier.padding(start = 6.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))

                            Row {
                                Icon(
                                    painter = painterResource(id = R.drawable.experience),
                                    contentDescription = "Experience",
                                    tint = Color(0xff87CEEB),
                                    modifier = Modifier
                                        .size(40.dp)
                                        .scale(1.5f)
                                )
                                Spacer(modifier = Modifier.width(2.dp))

                                Text(
                                    " Experience: 5 Years",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = Color.Black,
                                )
                            }
                            Row {
                                Text(
                                    text = "About Arya",
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(start = 6.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Row {
                            }
                            Spacer(modifier = Modifier.height(3.dp))

                            Row {
                                Text(
                                    text = "Working Preferences",
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(start = 6.dp)
                                )


                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            LazyRow {
                                item {
                                    Card(
                                        colors = CardDefaults.cardColors(Color(0xff87CEEB)),
                                        elevation = CardDefaults.cardElevation(5.dp),
                                        modifier = Modifier
                                            .padding(start = 6.dp)
                                            .clip(RoundedCornerShape(10.dp))

                                    ) {
                                        val text = "Work Hours = 8 AM - 5 PM\n" +
                                                "Can ably take care of young children\n" +
                                                "Expert in managing laundry\n" +
                                                "Expert in preparing African Dishes"
                                        Text(
                                            text = text,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp,
                                            modifier = Modifier.padding(16.dp
                                            ),


                                            )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))



                                }


                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Card(
                                onClick = {

                                },
                                colors = CardDefaults.cardColors(Color(0xff87CEEB)),
                                elevation = CardDefaults.cardElevation(5.dp),
                                border = BorderStroke(1.dp, Color.Black),
                                modifier = Modifier
                                    .padding(start = 6.dp)
                                    .clip(RoundedCornerShape(10.dp))

                            ) {
                                Text(
                                    text = "Connect with Arya",
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                    modifier = Modifier.padding(
                                        start = 50.dp,
                                        top = 10.dp,
                                        end = 50.dp,
                                    )
                                )
                            }


                        }
                    }


                }
            }
        )
    }
}








@Preview(showBackground = true)
@Composable
fun PreviewLight(){
    ProfilesScreen(rememberNavController())
}