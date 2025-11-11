package chin.pswm.gps.photo.location.map.activity.first_open.nav

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import chin.pswm.gps.photo.location.map.activity.first_open.common.CommonUtils.openWifiSetting
import chin.pswm.gps.photo.location.map.activity.first_open.cusom.NoInternetPopup
import chin.pswm.gps.photo.location.map.activity.first_open.ui.language.LanguageAltScreen
import chin.pswm.gps.photo.location.map.activity.first_open.ui.language.LanguageScreen
import chin.pswm.gps.photo.location.map.activity.first_open.ui.onboard.OnboardScreen
import chin.pswm.gps.photo.location.map.activity.first_open.ui.select.SelectAltScreen
import chin.pswm.gps.photo.location.map.activity.first_open.ui.select.SelectScreen
import chin.pswm.gps.photo.location.map.activity.first_open.ui.splash.SplashScreen
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.TrackingScreen
import chin.pswm.gps.photo.location.map.utils.LocalScreenTAG
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Composable
fun FirstOpenNavigation(modifier: Modifier = Modifier, intent: Intent) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val adsManager: AdsManager = remember {
        AdsManager.INSTANCE
    }

    NavHost(
        navController = navController,
        startDestination = Dest.Splash,
        modifier = modifier,
        enterTransition = { scaleIn(initialScale = 0.9f) + fadeIn() },
        exitTransition = { scaleOut(targetScale = 1f) + fadeOut() },
        popEnterTransition = { scaleIn(initialScale = 1f) + fadeIn() },
        popExitTransition = { scaleOut(targetScale = 0.9f) + fadeOut() }
    ) {

        composableWithTag<Dest.Splash>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() },
        ) {
            SplashScreen(navController, intent)
        }

        composableWithTag<Dest.Language>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() },
        ) {
            LanguageScreen(navController, true)
        }

        composableWithTag<Dest.LanguageAlt>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() },
        ) {
            val dest = it.toRoute<Dest.LanguageAlt>()
            LanguageAltScreen(navController, dest.code)
        }

        composableWithTag<Dest.Select>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() },
        ) {
            SelectScreen(navController)
        }

        composableWithTag<Dest.SelectAlt>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() },
        ) {
            SelectAltScreen(navController)
        }

        composableWithTag<Dest.OnBoard>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() },
        ) {
            OnboardScreen(navController)
        }
    }

    val isConnected = adsManager.isConnected.collectAsState().value
    NoInternetPopup(
        isShow = !isConnected,
        onGoSetting = {
            context.openWifiSetting()
        }
    )
}

@Suppress("DEPRECATION")
class CustomNavType<T : Parcelable>(
    private val clazz: KClass<T>,
    private val serializer: KSerializer<T>
) : NavType<T?>(true) {

    private val TAG: String
        get() = this::class.java.simpleName

    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, clazz.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): T? {
        timber.log.Timber.tag(TAG).d("parseValue: $value")
        if (value.isEmpty()) return null
        return Json.decodeFromString(serializer, value)
    }

    override fun put(bundle: Bundle, key: String, value: T?) {
        timber.log.Timber.tag(TAG).d("put: ")
        bundle.putParcelable(key, value)
    }
}

inline fun <reified T : Dest> NavGraphBuilder.composableWithTag(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        null,
    noinline exitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        null,
    noinline popEnterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        enterTransition,
    noinline popExitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        exitTransition,
    noinline sizeTransform:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?)? =
        null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {

    this.composable<T>(
        typeMap = typeMap,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform,
        content = { entry ->
            CompositionLocalProvider(
                LocalScreenTAG provides T::class.java.simpleName
            ) {
                val TAG = LocalScreenTAG.current
                TrackingScreen(TAG)
                content(entry)
            }
        }
    )
}

class CustomEnumNavType<T : Enum<T>>(
    private val clazz: KClass<T>,
    private val serializer: KSerializer<T>
) : NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T {
        return bundle.getSerializable(key) as T
    }

    override fun parseValue(value: String): T {
        return clazz.java.enumConstants!!.first { it.name == value }
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putSerializable(key, value)
    }
}