package net.ezra.ui.registration

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import net.ezra.navigation.ROUTE_REGISTER

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
        title = "History",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = false,
    ),

    BottomNavigationItem(
        title = "Updates",
        selectedIcon = Icons.Filled.Build,
        unselectedIcon = Icons.Outlined.Build,
        hasNews = false,
        badgeCount = 9
    ),

    BottomNavigationItem(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = false,
    ),

    )

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(navController: NavHostController) {
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
                        selected = index == selectedSecondListIndex,
                        onClick = {
                            selectedSecondListIndex = index
                            scope.launch {
                                drawerState.close()

                            }

                        },
                        icon = {
                            Icon(
                                imageVector =
                                if (index == selectedSecondListIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title,
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
                        .clip(RoundedCornerShape(30.dp)),

                    ) {
                items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                navController.navigate(item.title)

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
                                            Badge()
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
                LazyColumn {
                    item {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()

                        ) {
                            Card(
                                onClick = {
                                          navController.navigate(ROUTE_REGISTER)

                            },
                                colors = CardDefaults.cardColors(Color(0xffFFB6C1)),
                                elevation =CardDefaults.cardElevation(10.dp),
                                border = BorderStroke(2.dp, Color.Black),
                                )
                            {
                                Text(
                                    text = "House Manager or seeking to be hired",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 30.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(16.dp)
                                    )


                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Card(
                                onClick = {

                                },
                                colors = CardDefaults.cardColors(Color(0xff90ee90)),
                                elevation =CardDefaults.cardElevation(10.dp),
                                border = BorderStroke(2.dp, Color.Black),
                            )
                            {
                                Text(
                                    text = "Looking for a house manager??",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 30.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(16.dp)
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
fun PreviewLight() {
    RegistrationScreen(rememberNavController())

}