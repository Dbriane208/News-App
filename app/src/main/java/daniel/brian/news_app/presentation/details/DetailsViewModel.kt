package daniel.brian.news_app.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.brian.news_app.domain.model.Article
import daniel.brian.news_app.domain.usecases.news.NewsUseCases
import daniel.brian.news_app.domain.usecases.news.UpsertArticle
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {
    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.UpsertDeleteArticle ->{
                viewModelScope.launch {
                    val article = newsUseCases.selectArticleById(event.article.url)
                    if (article == null){
                        upsertArticle(event.article)
                    }else{
                        deleteArticle(event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffects ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect = "Article Deleted"
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        sideEffect = "Article Saved"
    }
}