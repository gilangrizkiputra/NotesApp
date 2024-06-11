@file:kotlin.OptIn(ExperimentalMaterial3Api::class)

package com.sukasrana.notesapp.view.presentation.home.component

import android.widget.AdapterView.OnItemClickListener
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sukasrana.notesapp.ui.theme.NotesAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesCard(
    title: String,
    date: String,
    onItemNotesClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onItemNotesClicked,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, shape = MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .height(30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                modifier = modifier
            )
            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                textAlign = TextAlign.End,
                modifier = modifier
            )
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//private fun PreviewNotesCard() {
//    NotesAppTheme {
//        NotesCard(title = "Judul", date = "27/09/24", onItemClicked = { /*TODO*/ })
//    }
//}

