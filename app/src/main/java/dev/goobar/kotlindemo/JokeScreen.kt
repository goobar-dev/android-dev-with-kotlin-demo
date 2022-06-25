package dev.goobar.kotlindemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.placeholder
import dev.goobar.kotlindemo.JokeScreenViewModel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeScreen(
  navController: NavController,
  viewModel: JokeScreenViewModel
) {

  val state by viewModel.state.collectAsState()

  val showRefresh by derivedStateOf { !state.isLoading }

  Scaffold(
    topBar = {
      SmallTopAppBar(
        title = { Text(text = "Joke") },
        navigationIcon = {
          IconButton(
            onClick = { navController.popBackStack() }
          ) {
              Icon(painterResource(R.drawable.ic_baseline_arrow_back_24), "Back button")
            }
        }
      )
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          if (!state.isLoading) {
            viewModel.refresh()
          }
        }
      ) {
        if (state.isLoading) {
          CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        } else {
          Icon(painter = painterResource(id = R.drawable.ic_baseline_refresh_24), contentDescription = "Refresh")
        }
      }
    }
  ) {
    JokeScreenContent(state, Modifier.padding(it))
  }
}

@Composable
private fun JokeScreenContent(state: UiState, modifier: Modifier = Modifier) {
  Column(modifier = Modifier
    .padding(horizontal = 20.dp)
    .then(modifier)) {
    Text(
      text = state.setup,
      modifier = Modifier
        .fillMaxWidth(1f)
        .placeholder(state.isLoading)
    )
    Spacer(modifier = Modifier.size(24.dp))
    Text(
      text = state.punchline,
      modifier = Modifier
        .fillMaxWidth(1f)
        .placeholder(state.isLoading)
    )
  }
}