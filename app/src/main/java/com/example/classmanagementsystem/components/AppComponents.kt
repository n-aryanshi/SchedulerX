package com.example.classmanagementsystem.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.data.NavigationItem
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.ui.theme.AccentColor
import com.example.classmanagementsystem.ui.theme.GrayColor
import com.example.classmanagementsystem.ui.theme.Primary
import com.example.classmanagementsystem.ui.theme.Secondary
import com.example.classmanagementsystem.ui.theme.TextColor
import com.example.classmanagementsystem.ui.theme.WhiteColor
import com.example.classmanagementsystem.ui.theme.componentShapes

@Composable
fun NormalTextComponent(value:String){
    Text(
        text = value,
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color= TextColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value:String){
    Text(
        text = value,
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color= TextColor,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ExperimentalMaterialApi")
@Composable
fun MyTextFieldComponent(labelValue:String , painterResource: Painter, onTextChanged: (String) -> Unit,errorStatus: Boolean = false){
    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small) ,
        label = {Text( text = labelValue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
             focusedBorderColor = Primary,
             focusedLabelColor = Primary,
             cursorColor = Primary,
//             backgroundColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription ="" )
        },
        isError = !errorStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ExperimentalMaterialApi")
@Composable
fun PasswordTextFieldComponent(labelValue:String , painterResource: Painter,onTextSelected: (String) -> Unit, errorStatus: Boolean = false){
    val localFocusManager= LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible =remember{
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small) ,
        label = {Text( text = labelValue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
//            backgroundColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions{
                localFocusManager.clearFocus()
        },
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription ="" )
        },
        trailingIcon = {
            val iconImage=if(passwordVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }
            val description=if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }else{
                stringResource(id = R.string.show_password)
            }
            IconButton(onClick = { passwordVisible.value=!passwordVisible.value}) {
                Icon(imageVector = iconImage,contentDescription = description)
            }
        },
        visualTransformation=if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Composable
fun CheckboxComponent(value:String, onTextSelected:(String)->Unit,onCheckedChange: (Boolean) -> Unit){
    Row( modifier= Modifier
        .fillMaxWidth()
        .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically 
    ){
        val checkedState = remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value=it
                onCheckedChange.invoke(it)
            }
        )
        ClickableTextComponent(value,onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value:String, onTextSelected:(String)->Unit){
    val initialText="By continuing you accept our "
    val privacyPolicyText="Privacy Policy "
    val andText="and "
    val termsText="Term of Use"
    val annotatedString= buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag=privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag=termsText, annotation = termsText)
            append(termsText)
        }
    }
    ClickableText(text = annotatedString, onClick = {
        offset-> annotatedString.getStringAnnotations(offset,offset)
        .firstOrNull()?.also {span->
            Log.d("ClickableTextComponent","{$span}")
            if(span.item ==termsText || span.item==privacyPolicyText){
                onTextSelected(span.item)
            }
        }
    })
}

@Composable
fun ButtonComponent(value:String, onButtonClicked: () -> Unit, isEnabled: Boolean = false){
    Button(onClick = { onButtonClicked.invoke() },
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors =  ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier= Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text=value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DividerTextComponent(){
    Row(
        modifier=Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Divider(
            modifier= Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )
        Text(
            text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = TextColor,
            modifier = Modifier.padding(8.dp)
            )
        Divider(
            modifier= Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean=true, onTextSelected:(String)->Unit){
    val initialText= if(tryingToLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText= if (tryingToLogin) "Login" else "Register"
    val annotatedString= buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag=loginText, annotation = loginText)
            append(loginText)
        }

    }
    ClickableText(
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = {
            offset-> annotatedString.getStringAnnotations(offset,offset)
        .firstOrNull()?.also {span->
            Log.d("ClickableTextComponent","{$span}")
            if(span.item ==loginText){
                onTextSelected(span.item)
            }
        }
    })
}


@Composable
fun UnderLinedTextComponent(value:String,onClick: () -> Unit){
    Text(
        text = value,
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .clickable(onClick=onClick),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color= GrayColor,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ExperimentalMaterialApi")
@Composable
fun AppToolbar(
    toolbarTitle: String, logoutButtonClicked: () -> Unit,
    navigationIconClicked: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Primary,
            titleContentColor = TextColor,
            navigationIconContentColor = TextColor
        ),
        title = {
            Text(
                text = toolbarTitle, color = WhiteColor
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu),
                    tint = Color.Black
                )
            }

        },
        actions = {
            IconButton(onClick = {
                AppRouter.navigateTo(Screen.SearchScreen)
            }) {
                Icon(imageVector = Icons.Filled.Search,contentDescription = "search")
            }
            IconButton(onClick = {
                AppRouter.navigateTo(Screen.Profile)
            }) {
                Icon(painter=painterResource(id = R.drawable.people),contentDescription = "profile")
            }
            IconButton(onClick = {
                logoutButtonClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = stringResource(id = R.string.logout),
                )
            }
        }
    )
}


@Composable
fun NavigationDrawerHeader(value: String?) {
    Box(
        modifier = Modifier
            .background(
                Brush.horizontalGradient(
                    listOf(Primary, Secondary)
                )
            )
            .fillMaxWidth()
            .height(180.dp)
            .padding(32.dp)
    ) {

        NavigationDrawerText(
            title = value?:stringResource(R.string.navigation_header), 28.sp , AccentColor
        )

    }
}

@Composable
fun NavigationDrawerBody(navigationDrawerItems: List<NavigationItem>,
                         onNavigationItemClicked:(NavigationItem) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {

        items(navigationDrawerItems) {
            NavigationItemRow(
                item = it,
                onNavigationItemClicked
            )
        }

    }
}

@Composable
fun NavigationItemRow(item: NavigationItem,
                      onNavigationItemClicked:(NavigationItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigationItemClicked.invoke(item)
            }.padding(all = 16.dp)
    ) {

        Icon(
            imageVector = item.icon,
            contentDescription = item.description,
        )

        Spacer(modifier = Modifier.width(18.dp))

        NavigationDrawerText(title = item.title, 18.sp, Primary)
    }
}

@Composable
fun NavigationDrawerText(title: String, textUnit: TextUnit, color: Color) {

    val shadowOffset = Offset(4f, 6f)

    Text(
        text = title,
        style = TextStyle(
            color = Color.Black,
            fontSize = textUnit,
            fontStyle = FontStyle.Normal,
            shadow = Shadow(
                color = Primary,
                offset = shadowOffset, 2f
            )
        )
    )
}

@Composable
fun BottomSheetItem(icon: ImageVector, title:String, onClick:()->Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier=Modifier.clickable{ onClick()}
    ){
        Icon(icon,contentDescription = null,tint= Primary)
        Text(text=title,color= Primary, fontSize = 22.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ExperimentalMaterialApi")
@Composable
fun FileTopBar(
    toolbarTitle: String,navigationIconClicked: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Primary,
            titleContentColor = TextColor,
            navigationIconContentColor = TextColor
        ),
        title = {
            Text(
                text = toolbarTitle, color = WhiteColor
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

        }
    )
}
