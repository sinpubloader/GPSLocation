package chin.pswm.gps.photo.location.map.ads.adunit.common

enum class AdErrorCode(val code: Int) {
    ERROR_CODE_NOT_HAVE_ADS(0),
    ERROR_CODE_SHOW_IN_BACKGROUND(1),
    ERROR_CODE_SHOW_NO_INTERNET_CONDITION(2),
    ERROR_CODE_PURCHASE_OR_NOT_CONSENT(3),
}