package chin.pswm.gps.photo.location.map.activity.first_open.common

import androidx.navigation.NavController
import androidx.navigation.navOptions
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import timber.log.Timber

object NavigationUtil {
    private val TAG: String
        get() = this::class.java.simpleName


    fun <T : Dest> NavController.safeNavigate(
        currentRound: T,
        destRoute: T,
        popUpTo: T? = null
    ) {
        Timber.tag(TAG).d("safeNavigate: $destRoute")
        val routeName = destRoute.javaClass.name.replace("$", ".")
        val routePopUpTo = popUpTo?.javaClass?.name?.replace("$", ".")
        val routeCurrentName = this.currentDestination?.route?.substringBefore("/")
        val currentRoundName = currentRound.javaClass.name.replace("$", ".")

        if (currentRoundName == routeCurrentName && routeName != routeCurrentName && (routePopUpTo == null || routePopUpTo == routeCurrentName)) {
            try {
                this.navigate(
                    route = destRoute,
                    navOptions = navOptions {
                        if (popUpTo != null) {
                            popUpTo(popUpTo) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun <T : Dest> NavController.safeNavigatePopupAll(
        route: T,
    ) {
        Timber.tag(TAG).d("safeNavigatePopupAll: $route")
        val routeName = route.javaClass.name.replace("$", ".")
        val routeCurrentName = this.currentDestination?.route?.substringBefore("/")
        if (routeName != routeCurrentName) {
            try {
                this.navigate(
                    route = route,
                    navOptions = navOptions {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                )
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun <T : Any> NavController.safeBackPress(route: T) {
        Timber.tag(TAG).d("safeBackPress: $route")
        val routeName = route.javaClass.name.replace("$", ".")
        val routeCurrentName = this.currentDestination?.route?.substringBefore("/")
        if (routeName == routeCurrentName) {
            try {
                this.popBackStack()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun <T : Any> NavController.safePopBackStack(route: T) {
        val routeName = route::class.java.superclass.name + ".${route}"
        if (this.currentDestination?.route != routeName) {
            try {
                this.popBackStack(route, inclusive = false, saveState = true)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}