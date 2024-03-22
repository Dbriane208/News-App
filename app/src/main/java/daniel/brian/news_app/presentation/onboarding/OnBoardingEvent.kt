package daniel.brian.news_app.presentation.onboarding

// contains the events that will be sent from UI to viewModel
sealed class OnBoardingEvent {
    data object  SaveAppEntry : OnBoardingEvent()
}