package com.mohit.gdsc.ipsacademy.ui.screens.homeScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.mohit.gdsc.ipsacademy.data.models.PastEventModel
import com.mohit.gdsc.ipsacademy.data.models.UpcomingEventModel

val viewModel = HomeScreenViewModel()

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UpcomingEventsDetailsCard(eventDetails: UpcomingEventModel) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(eventDetails.eventlink)) }
    Card(
        modifier = Modifier
            .padding(horizontal = 3.dp, vertical = 5.dp)
            .padding(2.dp),
        elevation = 6.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            context.startActivity(intent)
        }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .height(225.dp)
                .width(225.dp)
        ) {
            Card(elevation = 6.dp, modifier = Modifier.padding(15.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(eventDetails.thumbnaillink),
                    contentDescription = eventDetails.title,
                    modifier = Modifier
                        .size(125.dp)
                )
            }
            Text(
                text = eventDetails.title.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.width(150.dp)
            )
            Text(
                text = eventDetails.date.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                )
            )

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FutureEventsDetailsCard(eventDetails: PastEventModel) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(eventDetails.eventlink)) }

    Card(
        modifier = Modifier
            .padding(horizontal = 3.dp, vertical = 5.dp)
            .padding(2.dp),
        elevation = 6.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            context.startActivity(intent)
        }

    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .height(225.dp)
                .width(225.dp)
        ) {
            Card(elevation = 6.dp, modifier = Modifier.padding(15.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(eventDetails.thumbnaillink),
                    contentDescription = eventDetails.title,
                    modifier = Modifier
                        .size(125.dp)
                )
            }
            Text(
                text = eventDetails.title.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.width(150.dp)
            )
            Text(
                text = eventDetails.date.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                )
            )

        }
    }
}

@Composable
fun FutureEventsDetailsDetailsContent() {

    /*
    Also Use
    var events = viewModel.pastEvents.observeAsState()  [we have to pass there list as response]
    with this line 178-180 into 1 line 176
    */

    var events by remember { mutableStateOf(listOf<UpcomingEventModel>()) }

    viewModel.upcomingEvents.observe(LocalLifecycleOwner.current) {
        events = it.upcomingEventItems!!
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)
    ) {
        items(
            events
        ) {
            UpcomingEventsDetailsCard(eventDetails = it)
        }
    }
}


@SuppressLint("MutableCollectionMutableState")
@Composable
fun PastEventsDetailsDetailsContent() {

    /*
    Also Use
    var events = viewModel.pastEvents.observeAsState()  [we have to pass there list as response]
    with this line 178-180 into 1 line 176
     */

    var events by remember { mutableStateOf(listOf<PastEventModel>()) }

    viewModel.pastEvents.observe(LocalLifecycleOwner.current) {
        events = it.pastEventItems!!
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)
    ) {
        items(
            events
        ) {
            FutureEventsDetailsCard(eventDetails = it)
        }
    }
}



