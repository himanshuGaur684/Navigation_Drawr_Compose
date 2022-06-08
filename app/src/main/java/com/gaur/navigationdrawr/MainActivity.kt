package com.gaur.navigationdrawr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaur.navigationdrawr.ui.theme.NavigationDrawrTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawrTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Header()
                }
            }
        }
    }


}


@Composable
fun Header() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
        TopAppBar(title = { Text(text = "Navigation") },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                }
            }
        )
    },
        drawerContent = {
            DrawrContent()
        }
    ) {

    }

}


@Composable
fun DrawrContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Google Classroom",
            style = TextStyle(color = Color.Black, fontSize = 24.sp),
            modifier = Modifier.padding(12.dp)
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(.5.dp)
        )
        val list = listOf<DrawrContent>(
            DrawrContent(title = "Classes", imageVector = Icons.Default.Home),
            DrawrContent(title = "Calender", imageVector = Icons.Default.ShoppingCart),
            DrawrContent(title = "Notifications", imageVector = Icons.Default.Notifications),
            DrawrContent(title = "Offline files", imageVector = Icons.Default.CheckCircle),
            DrawrContent(title = "Archived Classes", imageVector = Icons.Default.ArrowDropDown),
            DrawrContent(title = "Classroom Folders", imageVector = Icons.Default.Refresh),
            DrawrContent(title = "Settings", imageVector = Icons.Default.Settings),
            DrawrContent(title = "Help", imageVector = Icons.Default.ThumbUp),
        )
        LazyColumn {
            items(list) {
                DrawrItem(it)

            }
        }
    }
}


@Composable
fun DrawrItem(it: DrawrContent) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Icon(
            imageVector = it.imageVector,
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = it.title,
            style = TextStyle(color = Color.Black, fontSize = 20.sp),
            modifier = Modifier.padding(8.dp)
        )
    }
}


data class DrawrContent(val title: String, val imageVector: ImageVector)




 