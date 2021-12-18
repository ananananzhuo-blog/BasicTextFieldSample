
## 概览
众所周知Compose中默认的TextField和OutlineTextField样式并不能满足所有的使用场景，所以自定义TextField就成了必备技能，本文就揭露一下自定义TextField的实现。自定义TextField主要使用BasicTextField实现。

## 简单自定义BasicTextField示例
1. 代码

```kt
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
```
2. 效果
   ![](https://files.mdnice.com/user/15648/99dbd038-2ad0-4ea2-8083-5b7257947501.gif)

## 实现自定义样式的BasicTextField
我们知道BasicTextField提供了基础的输入框，只包含文字输入，光标等简单功能，如果我们需要增加样式就需要自己实现decorationBox参数，来添加样式。

本例中我们实现一个带蓝色边框，内部填充绿色，左侧有图标的自定义BasicTextField。

1. 代码

```kt
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
            }
        )
    }
}
```
2. 效果
   ![](https://files.mdnice.com/user/15648/05abe736-77da-4c6d-8623-9cbf2a4645b8.gif)


## 使用BasicTextField自定义百度输入框
1. 代码

```kt

@Composable
fun BaiduTextField() {
    var text by remember {
        mutableStateOf("安安安安卓")
    }
    BasicTextField(value = text, onValueChange = {
        text = it
    }, decorationBox = { innerTextField ->
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
                    .fillMaxHeight()
                ,
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
                    modifier = iconModifier.padding(end = 8.dp).size(20.dp),
                    tint = Color.Gray
                )
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .fillMaxHeight()
                        .background(
                            color = Color.Blue,
                            shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                        ).clickable {

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
```
## 效果



![](https://files.mdnice.com/user/15648/3aff5ee3-3852-42c2-8816-6dc8a7c45b49.gif)







