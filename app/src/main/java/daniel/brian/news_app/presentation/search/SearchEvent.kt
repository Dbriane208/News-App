package daniel.brian.news_app.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    data object SearchNews: SearchEvent()
}