package daniel.brian.news_app.presentation.details

sealed class DetailsEvent {
    data object SaveArticle: DetailsEvent()
}