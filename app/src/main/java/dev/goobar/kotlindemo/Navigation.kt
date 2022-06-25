package dev.goobar.kotlindemo

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.goobar.kotlindemo.Destination.JOKE

internal enum class Destination {
  HOME, JOKE
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.destination(destination: Destination, content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit) {
  composable(
      destination.name,
      enterTransition = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
      },
      exitTransition = {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
      },
      popEnterTransition = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
      },
      popExitTransition = {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
      },
      content = content
  )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph() {
  val navController = rememberAnimatedNavController()
  AnimatedNavHost(navController, startDestination = Destination.HOME.name) {

    composable(Destination.HOME.name) {
      HomeScreen(navController)
    }

    destination(JOKE) {
      val viewModel = hiltViewModel<JokeScreenViewModel>()
      JokeScreen(navController, viewModel)
    }

  }
}