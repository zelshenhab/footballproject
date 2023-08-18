package com.footballproject.ui.screen.team

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.footballproject.R
import com.footballproject.ui.screen.util.ErrorCompose
import com.footballproject.ui.screen.util.LoadingCompose
import com.footballproject.ui.theme.custom.CustomTheme


@Composable
fun TeamScreen(
    teamId: Int?,
    viewModel: TeamViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (teamId != null) {
        LaunchedEffect(viewModel) {
            viewModel.event(TeamEvent.OnCreate(teamId))
        }
    } else {
        viewModel.event(TeamEvent.OnError("id==null"))
    }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground),
    )
    {
        item {
            LoadingCompose(state.loading)
            ErrorCompose(state.error)
        }
        item {

            TeamInfo(viewState = state)
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TeamInfo(viewState: TeamViewState) {
    if (viewState.team != null) {

        val teamItem = viewState.team
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(),
        ) {

            if (teamItem.crest != null && !teamItem.crest.endsWith(".svg")) {
                GlideImage(
                    model = teamItem.crest,
                    contentDescription = "",
                    modifier = Modifier
                        .height(320.dp)
                        .width(180.dp)
                        .padding(vertical = 2.dp)
                        .clip(RoundedCornerShape(2.dp))

                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.no_image),
                    contentDescription = "",
                    modifier = Modifier
                        .height(320.dp)
                        .width(180.dp)
                        .padding(vertical = 2.dp)
                        .clip(RoundedCornerShape(2.dp))
                )
            }
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),

                ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(CustomTheme.colors.secondaryBackground)
                        .padding(vertical = 8.dp)

                ) {
                    Text(
                        text = teamItem.name,
                        color = CustomTheme.colors.primaryText,
                        style = CustomTheme.typography.body,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )

                    InfoText(title = stringResource(R.string.country), text = teamItem.area?.name?: "")

                    InfoText(title = stringResource(R.string.tla), text = teamItem.tla?: "")

                    InfoText(title = stringResource(R.string.address), text = teamItem.address?: "")

                    InfoText(title = stringResource(R.string.website), text = teamItem.website?: "")

                    InfoText(title = stringResource(R.string.founded), text = "${teamItem.founded}")

                    InfoText(title = stringResource(R.string.club_colors), text = teamItem.clubColors?: "")

                    InfoText(title = stringResource(R.string.venue), text = teamItem.venue?: "")
                }
            }

        }
//        Card(
//            elevation = CardDefaults.cardElevation(4.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 8.dp),
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(CustomTheme.colors.secondaryBackground)
//                    .padding(vertical = 8.dp)
//            ) {
//                Text(
//                    text = viewState.anime.synopsis,
//                    color = CustomTheme.colors.primaryText,
//                    style = CustomTheme.typography.body,
//                    modifier = Modifier
//                        .padding(horizontal = 8.dp)
//                )
//            }
//        }
    }
}

@Composable
private fun InfoText(title: String, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = CustomTheme.colors.primaryText,
            style = CustomTheme.typography.body,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(2f),
        )
        Text(
            text = text,
            color = CustomTheme.colors.primaryText,
            style = CustomTheme.typography.body,
            modifier = Modifier
                .weight(3f),
        )
    }
}
