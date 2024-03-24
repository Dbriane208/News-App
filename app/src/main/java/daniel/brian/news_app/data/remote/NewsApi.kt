package daniel.brian.news_app.data.remote

import daniel.brian.news_app.data.remote.dtos.NewsResponse
import daniel.brian.news_app.presentation.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET
    suspend fun getNews(
        @Query("page") page:Int,
        @Query("sources") sources:String,
        @Query("apiKey") apiKey:String = API_KEY
    ): NewsResponse
}