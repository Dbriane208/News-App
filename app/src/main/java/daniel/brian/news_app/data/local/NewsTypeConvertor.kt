package daniel.brian.news_app.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import daniel.brian.news_app.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConvertor {
    @TypeConverter
    fun sourceToString(source: Source): String{
        // combining the string using a comma
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source{
        return source.split(",").let{ sourceArray ->
            Source(sourceArray[0],sourceArray[1])
        }
    }
}