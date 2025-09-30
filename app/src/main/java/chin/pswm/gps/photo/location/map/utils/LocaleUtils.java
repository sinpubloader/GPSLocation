package chin.pswm.gps.photo.location.map.utils;

import java.util.Locale;

public class LocaleUtils {

    /**
     * Returns the display name of the language tag (e.g., "en" -> "English"),
     * capitalized according to the default locale.
     * * Kotlin equivalent: fun name(code: String): String
     *
     * @param code The BCP 47 language tag (e.g., "en", "es", "ms-MY").
     * @return The capitalized display language name.
     */
    public static String name(String code) {
        // 1. Get the display language (e.g., "english")
        String displayLanguage = Locale.forLanguageTag(code).getDisplayLanguage();

        if (displayLanguage.isEmpty()) {
            return "";
        }

        // 2. Capitalize the first letter (equivalent to Kotlin's replaceFirstChar { ... })
        // We use the default locale for titlecasing as the Kotlin code does.
        return displayLanguage.substring(0, 1).toUpperCase(Locale.getDefault()) +
                displayLanguage.substring(1);
    }

    /**
     * Returns the display name of the language, displayed in the *language itself* * (e.g., nameBase("en") -> "English", nameBase("de") -> "Deutsch").
     * If the tag includes a country (e.g., "ms-MY"), it returns the display country
     * in the language itself (e.g., "Malaysia"). If the country is empty, it
     * returns the display language.
     * * Kotlin equivalent: fun nameBase(code: String): String
     *
     * @param code The BCP 47 language tag (e.g., "en", "ms-MY").
     * @return The base name (language or country) in its native form.
     */
    public static String nameBase(String code) {
        // Create the locale from the code
        Locale locale = Locale.forLanguageTag(code);

        // Get the display language in the language itself (the native name)
        String defaultName = locale.getDisplayLanguage(locale);

        // Check if it's a regional code (e.g., "ms-MY")
        if (code.contains("-")) {
            // Get the display country in the language itself
            String countryName = locale.getDisplayCountry(locale);

            // Equivalent to Kotlin's country.ifEmpty { default }
            return countryName.isEmpty() ? defaultName : countryName;
        } else {
            // It's a base language code (e.g., "en")
            return defaultName;
        }
    }
}