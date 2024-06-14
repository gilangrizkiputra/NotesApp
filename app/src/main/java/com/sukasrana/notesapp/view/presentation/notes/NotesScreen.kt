package com.sukasrana.notesapp.view.presentation.notes

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sukasrana.notesapp.data.local.entity.NotesEntity
import com.sukasrana.notesapp.ui.theme.NotesAppTheme
import com.sukasrana.notesapp.utils.Converter.changeMillisToDateString
import com.sukasrana.notesapp.utils.ViewModelFactory
import com.sukasrana.notesapp.view.presentation.notes.component.NotesDatePicker
import com.sukasrana.notesapp.view.presentation.notes.component.TopAppBarNotes
import java.time.Instant

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    id: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val notesViewModel: NotesViewModel =
        viewModel(factory = ViewModelFactory.getInstance(context = context))
    val state by notesViewModel.state.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)

    LaunchedEffect(id) {
        notesViewModel.onEvent(NotesEvent.OnGetNotesById(id))
    }

    NotesContent(
        isNotesExist = state.currentNotesId != null,
        onBackClick = { navController.navigateUp() },
        onDeleteClick = {
            state.currentNotesId?.let { notesViewModel.deleteNotes(it) }
            navController.navigateUp()
        },
        title = state.title,
        onTitleChange = { notesViewModel.onEvent(NotesEvent.OnTitleChange(it)) },
        description = state.description,
        onDescriptionChange = { notesViewModel.onEvent(NotesEvent.OnDescriptionChange(it)) },
        dueDate = state.dueDate,
        onSaveClick = {
            val notes = NotesEntity(
                notesId = state.currentNotesId,
                title = state.title,
                description = state.description,
                dueDate = state.dueDate ?: Instant.now().toEpochMilli()
            )

            if (state.title.isNotEmpty() && state.description.isNotEmpty()) {
                notesViewModel.saveNotes(notes)
                navController.navigateUp()
            } else
                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesContent(
    isNotesExist: Boolean,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    dueDate: Long?,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBarNotes(
                isNotesExist = isNotesExist,
                onBackButtonClick = onBackClick,
                onDeleteButtonClick = onDeleteClick,
                onSaveButtonClick = onSaveClick
            )
        },
        modifier = modifier
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dueDate.changeMillisToDateString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            OutlinedTextField(
                value = title,
                onValueChange = onTitleChange,
                label = { Text(text = "Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent
                )
            )
            Divider(color = Color.Black, thickness = 1.dp)
            Spacer(modifier = Modifier.padding(4.dp))
            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                label = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewNotesScreen() {

    val lifecycleOwner = remember {
        object : LifecycleOwner {
            private val lifecycleRegistry = LifecycleRegistry(this)
            fun getLifecycle() = lifecycleRegistry.apply {
                currentState = Lifecycle.State.RESUMED
            }

            override val lifecycle: Lifecycle
                get() = getLifecycle()
        }
    }

    NotesAppTheme {
        NotesScreen(
            id = 1,
            navController = rememberNavController(),
            lifecycleOwner = lifecycleOwner
        )
    }

}
