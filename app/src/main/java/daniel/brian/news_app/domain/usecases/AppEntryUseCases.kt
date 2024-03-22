package daniel.brian.news_app.domain.usecases

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
) {
}