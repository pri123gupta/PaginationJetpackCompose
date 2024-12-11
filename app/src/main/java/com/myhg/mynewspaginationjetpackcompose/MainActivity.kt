package com.myhg.mynewspaginationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.myhg.mynewspaginationjetpackcompose.model.Data
import com.myhg.mynewspaginationjetpackcompose.ui.theme.MyNewsPaginationJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNewsPaginationJetpackComposeTheme {
                HomeScreen()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {

    val resFlow = viewModel.pager
    val res: LazyPagingItems<Data> = resFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(count = res.itemCount) { index ->
            val item = res[index]
            NewsItem(item!!, index)
        }
        res.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                Modifier
                                    .padding(12.dp)
                                    .align(Alignment.Center).background(Color.Cyan)
                            )
                        }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(Modifier.fillMaxWidth().height(100.dp)) {
                            CircularProgressIndicator(
                                Modifier
                                    .padding(12.dp)
                                    .align(Alignment.Center).background(Color.Yellow)
                            )
                        }
                    }
                }

                loadState.prepend is LoadState.Loading -> {
                    item {
                        Box(Modifier.fillMaxWidth().height(100.dp)) {
                            CircularProgressIndicator(
                                Modifier
                                    .padding(12.dp)
                                    .align(Alignment.Center).background(Color.Red)
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun NewsItem(item: Data? = null, index: Int) {
    Column (Modifier.padding(4.dp)) {
        Text(
            modifier = Modifier.padding(2.dp),
            text = "$index) " +item?.airline?.get(0)?.country!! ,
            style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Medium)
        )
        Text(
            modifier = Modifier.padding(2.dp),
            text = item.airline.get(0).slogan,
            style = TextStyle(color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        )

        Divider()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyNewsPaginationJetpackComposeTheme {
        Greeting("Android")
    }
}