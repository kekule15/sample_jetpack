package com.example.mybizcard

import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.mybizcard.ui.theme.MyBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyBizCardWidget()
                }
            }
        }
    }
}

@Composable
fun MyBizCardWidget() {
    val showPortFolioWidget = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(corner = CornerSize(10.dp))
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                MyImageWidget()
                Divider()
                MyCardInfoWidget()
                Button(
                    onClick = {
                        showPortFolioWidget.value = !showPortFolioWidget.value
                    },

                    ) {
                    Text(text = "Projects", style = MaterialTheme.typography.button)
                }
                if (showPortFolioWidget.value)
                    PortFolioListWidget() else {
                    Box() {

                    }
                }
            }

        }
    }
}


@Composable
fun PortFolioListWidget() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
        border = BorderStroke(3.dp, color = Color.LightGray)
    ) {
        ListWidget(data = listOf("Project 1", "Project 2", "project 3", "Project 4", "Project 5", "project 6"))
    }
}

@Composable
fun ListWidget(data: List<String>) {
    LazyColumn(
        modifier = Modifier.padding(10.dp),
       
    ) {
        items(data) { item ->
            Card(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {
                Row(

                ) {
                    MyImageWidget(80.dp)
                    Column(
                        modifier = Modifier.padding(10.dp).align(alignment = Alignment.CenterVertically),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(item)
                        Text("A great project indeed")
                    }

                }
            }
        }
    }

}

@Composable
private fun MyCardInfoWidget() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Augustus Onyekachi",
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "Senior Software Engineer",
            modifier = Modifier.padding(1.dp),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "@AugustusOnyeka1",
            modifier = Modifier.padding(1.dp),
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
private fun MyImageWidget(size : Dp = 150.dp) {
    Surface(
        modifier = Modifier
            .size(size)
            .padding(10.dp),
        shape = CircleShape,
        border = BorderStroke(4.dp, color = Color.LightGray),
        elevation = 5.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.pic_image),
            contentDescription = "profile Image",
            modifier = Modifier.size(size),
            contentScale = ContentScale.Crop

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyBizCardTheme {
        MyBizCardWidget()
    }
}