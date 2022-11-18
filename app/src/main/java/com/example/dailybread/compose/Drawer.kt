package com.example.dailybread.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DrawerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch


sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object Inventory : DrawerScreens("Inventory", "inventory")
    object Account : DrawerScreens( "Account", "account")
    object Logout : DrawerScreens( "Log out", "logout")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Inventory,
    DrawerScreens.Account,
    DrawerScreens.Logout

)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)

    ) {
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.route)
                }

            )
        }
    }
}

//
//val openDrawer: () -> Unit = {
//    scope.launch {
//        drawerState.open()
//    }
//}
@Composable
fun MyModalDrawer(
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable () -> Unit){
    val scope = rememberCoroutineScope()
    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerBackgroundColor = Color(0xFFFCF7EC),

        drawerContent = {
            Drawer(
                onDestinationClicked = { route ->
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
//                    {
//                        popUpTo = navController.graph.startDestinationId
//                        launchSingleTop = true
//                    }
                }
            )
        }
    ){
        content()
    }
}





