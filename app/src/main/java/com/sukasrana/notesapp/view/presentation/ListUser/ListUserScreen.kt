package com.sukasrana.notesapp.view.presentation.ListUser

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sukasrana.notesapp.data.remote.response.User
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sukasrana.notesapp.view.presentation.ListUser.component.UserCard

@Composable
fun ListUserScreen(
    navController: NavController,
    viewModel: ListUserViewModel = hiltViewModel()
) {
    val users by viewModel.users.observeAsState(initial = emptyList())

    if (users.isEmpty()){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Wait for the retrofit list",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            )
        }
    } else {
        UserList(
            navController = navController,
            modifier = Modifier,
            users = users
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(
    navController: NavController,
    modifier: Modifier,
    users: List<User>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Retrofit") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }

    ){ contentPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
            ) {
                items(users) { user ->
                    UserCard(user = user)
                }
            }
        }
    }
}
