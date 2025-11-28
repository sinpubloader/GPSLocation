package chin.pswm.gps.photo.location.map.languegess

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateListOf
import java.util.Locale
import kotlin.text.ifEmpty

object LanguageState {

    val state by lazy {
        LazyListState(0, 0)
    }

    fun name(code: String): String {
        return Locale.forLanguageTag(code).displayLanguage.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    }

    fun nameBase(code: String): String {
        val default =
            Locale.forLanguageTag(code).getDisplayLanguage(Locale.forLanguageTag(code))
        return if (code.contains("-")) {
            val country =
                Locale.forLanguageTag(code).getDisplayCountry(Locale.forLanguageTag(code))
            country.ifEmpty { default }
        } else default
    }

    val codes by lazy {
        mutableStateListOf("en", "fr", "in", "pt", "es", "hi", "it", "ms", "ja", "ko", "de", "ar", "fa", "ru", "zh", "tr", "cs", "nl", "vi", "hu", "ro", "pl", "bg", "el", "sk", "da", "iw", "hr", "sl", "sv", "ca", "uk", "th", "no", "fi", /*"ms-BN", "ms-MY", "ms-SG", "ms-ID", "ur-IN", "ur-PK",*/ "bn", "km", "my", "az", "uz", "gu", "ta", "te", "mr", "kn", "or", "ml","sq"/*,"spi"*/)
    }

    val languages = listOf(
        "en" to "English",
        "fr" to "French",
        "in" to "Indonesian",
        "pt" to "Portuguese",
        "es" to "Spanish",
        "hi" to "Hindi",
        "it" to "Italian",
        "ms" to "Malay",
        "ja" to "Japanese",
        "ko" to "Korean",
        "de" to "German",
        "ar" to "Arabic",
        "fa" to "Persian",
        "ru" to "Russian",
        "zh" to "Chinese",
        "tr" to "Turkish",
        "cs" to "Czech",
        "nl" to "Dutch",
        "vi" to "Vietnamese",
        "hu" to "Hungarian",
        "ro" to "Romanian",
        "pl" to "Polish",
        "bg" to "Bulgarian",
        "el" to "Greek",
        "sk" to "Slovak",
        "da" to "Danish",
        "iw" to "Hebrew",
        "hr" to "Croatian",
        "sl" to "Slovenian",
        "sv" to "Swedish",
        "ca" to "Catalan",
        "uk" to "Ukrainian",
        "th" to "Thai",
        "no" to "Norwegian",
        "fi" to "Finnish",
        "bn" to "Bengali",
        "km" to "Khmer",
        "my" to "Burmese",
        "az" to "Azerbaijani",
        "uz" to "Uzbek",
        "gu" to "Gujarati",
        "ta" to "Tamil",
        "te" to "Telugu",
        "mr" to "Marathi",
        "kn" to "Kannada",
        "or" to "Odia",
        "ml" to "Malayalam",
        "ur" to "Urdu",
        "sq" to "Albanian"
    )
}