package com.ananananzhuo.basictextfieldsample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ananananzhuo.basictextfieldsample.ui.theme.BasicTextFieldSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTextFieldSampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    LazyColumn(content = {
        item {
            Text(text = "简单实现BasicTextField")
        }
        item {
            SimpleBasicTextField()
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(text = "实现带装饰的BasicTextField")
        }
        item {
            DecorateTextField()
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(text = "实现百度搜索的输入框")
        }
        item {
            BaiduTextField()
        }
    })
}

@Composable
fun BaiduTextField() {
    var text by remember {
        mutableStateOf("安安安安卓")
    }
    BasicTextField(value = text, onValueChange = {
        text = it
    },
        singleLine = true,
        decorationBox = { innerTextField ->
            val iconModifier = Modifier.padding(start = 5.dp)
            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 3.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 5.dp)
                    .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                }
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cha),
                        "",
                        modifier = iconModifier.size(20.dp),
                        tint = Color.Gray
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.record),
                        "",
                        modifier = iconModifier.size(20.dp),
                        tint = Color.Gray
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.takepic),
                        "",
                        modifier = iconModifier
                            .padding(end = 8.dp)
                            .size(20.dp),
                        tint = Color.Gray
                    )
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .fillMaxHeight()
                            .background(
                                color = Color.Blue,
                                shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                            )
                            .clickable {

                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "百度一下",
                            color = Color.White
                        )
                    }
                }
            }
        })
}

/**
 * 简单实现TextField效果
 */
@Composable
fun SimpleBasicTextField() {
    var text by remember {
        mutableStateOf("简单的TextField")
    }
    BasicTextField(
        value = text, onValueChange = {
            text = it
        },
        modifier = Modifier
            .height(40.dp)
            .width(300.dp)
            .background(Color.Green)
    )
}

@Composable
fun DecorateTextField() {
    var text by rememberSaveable {
        mutableStateOf("init")
    }
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            textStyle = TextStyle(color = Color.White),
            cursorBrush = SolidColor(Color.Blue),
            decorationBox = { innerTextField ->//decorationBox内部负责编写输入框样式
                Row(
                    Modifier
                        .padding(2.dp)
                        .fillMaxWidth()
                        .background(Color.Blue, RoundedCornerShape(percent = 30))
                        .padding(1.dp)
                        .background(Color.Green, RoundedCornerShape(percent = 29)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Star, tint = Color.White, contentDescription = null)
                    Spacer(Modifier.width(5.dp))
                    Box(modifier = Modifier.padding(top = 7.dp, bottom = 7.dp, end = 7.dp)) {
                        innerTextField()//自定义样式这行代码是关键，没有这一行输入文字后无法展示，光标也看不到
                    }
                }
            },
        )
    }
}

@Composable
@Preview
fun DecoratedTextFieldPreview() {
    Box(Modifier.fillMaxSize()) {
        DecorateTextField()
    }
}


fun toast(msg: String) {
//    Toast.makeText()
}