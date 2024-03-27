package daniel.brian.news_app.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import daniel.brian.news_app.presentation.bookmark.BookmarkScreen
import daniel.brian.news_app.presentation.bookmark.BookmarkViewModel
import daniel.brian.news_app.presentation.home.HomeScreen
import daniel.brian.news_app.presentation.home.HomeViewModel
import daniel.brian.news_app.presentation.onboarding.OnBoardingViewModel
import daniel.brian.news_app.presentation.onboarding.OnboardingScreen
import daniel.brian.news_app.presentation.search.SearchScreen
import daniel.brian.news_app.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                // using :: instead of opening the curly braces
                OnboardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            composable(route = Route.NewsNavigatorScreen.route){
//                val viewModel: HomeViewModel = hiltViewModel()
//                val articles = viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(articles = articles, navigate = {})
//                val viewModel: SearchViewModel = hiltViewModel()
//                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})
                  val viewModel: BookmarkViewModel = hiltViewModel()
                  BookmarkScreen(state = viewModel.state.value, navigate = {})

                }
            }
        }
    }
