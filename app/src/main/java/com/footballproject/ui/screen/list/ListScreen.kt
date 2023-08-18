package com.footballproject.ui.screen.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.footballproject.R
import com.footballproject.data.remote.response.teams.Team
import com.footballproject.ui.screen.util.ErrorCompose
import com.footballproject.ui.screen.util.LoadingCompose
import com.footballproject.ui.theme.custom.CustomTheme

@Composable
private fun ListScreenActions(
    navController: NavController,
    viewAction: ListAction?,
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            is ListAction.NavigateToTeam -> navController.navigate("team/${viewAction.id}")
            null -> Unit
        }
    }
}

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    LaunchedEffect(viewModel) {
        viewModel.event(ListEvent.OnCreate)
    }

    ListScreenActions(
        navController = navController,
        viewAction = action
    )

    ScreenView(
        viewState = state,
        eventHandler = viewModel::event
    )
}

@Composable
private fun ScreenView(
    viewState: ListViewState,
    eventHandler: (ListEvent) -> Unit,
) {

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground),
    ) {
        item {
            LoadingCompose(viewState.loading)
            ErrorCompose(viewState.error)
        }
        item {
            TextCenter(
                text = stringResource(R.string.football_teams),
                isVisible = viewState.teams.isNotEmpty()
            )
        }
        items(viewState.teams) { team ->
            TeamItem(team = team) {
                eventHandler.invoke(ListEvent.OnTeamClick(team.id))
            }
        }
    }
}

@Composable
private fun TextCenter(
    text: String,
    isVisible: Boolean = true,
) {
    if (isVisible) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                color = CustomTheme.colors.primaryText,
                style = CustomTheme.typography.large,
            )
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun TeamItem(
    team: Team,
    onClick: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(team.id)
            }
            .padding(horizontal = 8.dp)
            .background(CustomTheme.colors.secondaryBackground),

        verticalAlignment = Alignment.CenterVertically
    ) {
        if (team.crest != null && !team.crest.endsWith(".svg")) {
            GlideImage(
                model = team.crest,
                contentDescription = team.name,
                modifier = Modifier
                    .height(150.dp)
                    .width(100.dp)
                    .padding(vertical = 2.dp)

            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.no_image),
                contentDescription = "",
                modifier = Modifier
                    .height(150.dp)
                    .width(100.dp)
                    .padding(vertical = 2.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(
                text = team.name,
                color = CustomTheme.colors.primaryText,
                style = CustomTheme.typography.heading
            )
        }
    }
    ItemDivider()
}

@Composable
private fun ItemDivider() {
    Divider(
        modifier = Modifier.padding(1.dp),
        thickness = 0.5.dp,
        color = CustomTheme.colors.tintColor
    )
}
