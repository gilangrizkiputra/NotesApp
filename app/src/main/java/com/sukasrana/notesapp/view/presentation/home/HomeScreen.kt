package com.sukasrana.notesapp.view.presentation.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sukasrana.notesapp.R
import com.sukasrana.notesapp.data.local.entity.NotesEntity
import com.sukasrana.notesapp.ui.theme.NotesAppTheme
import com.sukasrana.notesapp.utils.Converter.changeMillisToDateString
import com.sukasrana.notesapp.utils.ViewModelFactory
import com.sukasrana.notesapp.view.presentation.home.component.NotesCard
import com.sukasrana.notesapp.view.presentation.navigation.Screen
import com.sukasrana.notesapp.viewModel.AuthViewModel

@Composable
fun HomeScreen(
    viewModel: AuthViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    isDarkMode: Boolean = isSystemInDarkTheme(),
    listState: LazyListState = rememberLazyListState(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val homeViewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )

    val state by homeViewModel.state.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
    val isFABExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    HomeContent(
        viewModel = viewModel,
        tasks = state.notes,
        isDarkMode = isDarkMode,
        isFABExpanded = isFABExpanded,
        navController = navController,
        onFabClicked = {
            navController.navigate(Screen.Notes.createRoute(0))
        },
        onListClicked = {
                        navController.navigate(Screen.User.route)
        },
        onMapsClicked = {
                        navController.navigate(Screen.Maps.route)
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    viewModel: AuthViewModel,
    tasks: List<NotesEntity>,
    isDarkMode: Boolean,
    isFABExpanded: Boolean,
    navController: NavController,
    onFabClicked: () -> Unit,
    onListClicked: () -> Unit,
    onMapsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = onMapsClicked) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "location"
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = onListClicked) {
                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = "list Retrofit"
                            )
                        }
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.size(25.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_oclock),
                                contentDescription = "Settings"
                            )
                        }
                        IconButton(onClick = { isVisible = !isVisible  }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings"
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClicked,
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                contentColor = Color.Black,
                containerColor = Color.Gray
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Notes"
                )
            }
        },
        modifier = modifier
    ) { contentPadding ->
        if (tasks.isEmpty())
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.image_notes),
                    contentDescription = "Task Image",
                    modifier = Modifier.height(200.dp)
                )
            }
        else
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.padding(contentPadding)
            ) {
                items(tasks, key = { it.notesId ?: 0 }) {
                    NotesCard(
                        title = it.title,
                        date = it.dueDate.changeMillisToDateString(),
                        onItemNotesClicked = { navController.navigate(Screen.Notes.createRoute(it.notesId)) }
                    )
                }
            }
    }
    if(isVisible)
    Popup(
        offset = IntOffset(600, 80)
    ) {
        Box {
            Button(onClick = {
                viewModel?.logout()
                navController.navigate(Screen.Login.route){
                    popUpTo(Screen.Home.route){inclusive=true}
                }
            }) {
                Text(text = "LogOut")
            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//private fun PreviewHomeScreen() {
    // Mocking a lifecycle owner for the preview
    //val lifecycleOwner = remember {
      //  object : LifecycleOwner {
        //    private val lifecycleRegistry = LifecycleRegistry(this)
          //  fun getLifecycle() = lifecycleRegistry.apply {
           //     currentState = Lifecycle.State.RESUMED
            //}

//            override val lifecycle: Lifecycle
 //               get() = getLifecycle()
   //     }
//    }
  //  NotesAppTheme {
    //    HomeScreen(
      //      viewModel = AuthViewModel,
        //    navController = rememberNavController(),
          //  context = LocalContext.current,
            //isDarkMode = isSystemInDarkTheme(),
            //listState = rememberLazyListState(),
            //lifecycleOwner = lifecycleOwner
        //)
    //}
//}
