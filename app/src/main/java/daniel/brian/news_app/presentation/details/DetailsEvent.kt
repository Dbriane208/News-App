package daniel.brian.news_app.presentation.details

import daniel.brian.news_app.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article): DetailsEvent()
    data object RemoveSideEffects: DetailsEvent()
}