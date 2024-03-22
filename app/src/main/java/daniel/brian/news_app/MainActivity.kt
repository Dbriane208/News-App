package daniel.brian.news_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import daniel.brian.news_app.domain.usecases.AppEntryUseCases
import daniel.brian.news_app.domain.usecases.ReadAppEntry
import daniel.brian.news_app.presentation.onboarding.OnBoardingViewModel
import daniel.brian.news_app.presentation.onboarding.OnboardingScreen
import daniel.brian.news_app.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        installSplashScreen()
        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect{
                Log.d("Test",it.toString())
            }
        }
        setContent {
            NewsAppTheme {
                Box(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                ) {
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    // using :: instead of opening the curly braces
                    OnboardingScreen(event = viewModel::onEvent)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppTheme {

    }
}