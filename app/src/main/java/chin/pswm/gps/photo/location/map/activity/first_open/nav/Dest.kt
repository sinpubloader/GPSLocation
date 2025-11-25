package chin.pswm.gps.photo.location.map.activity.first_open.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class Dest {
    @Serializable
    object Splash : Dest()

    @Serializable
    object Language : Dest()

    @Serializable
    data class LanguageAlt(val code: String) : Dest()

    @Serializable
    object LanguageSetting : Dest()

    @Serializable
    object Select : Dest()

    @Serializable
    object SelectAlt : Dest()

    @Serializable
    object OnBoard : Dest()

    @Serializable
    object Main : Dest()

    @Serializable
    object UninstallExploreFeature : Dest()

    @Serializable
    object UninstallVote : Dest()
}