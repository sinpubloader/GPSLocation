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
}