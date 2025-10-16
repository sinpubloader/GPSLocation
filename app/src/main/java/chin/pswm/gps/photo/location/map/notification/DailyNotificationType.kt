package chin.pswm.gps.photo.location.map.notification

import androidx.annotation.StringRes
import chin.pswm.gps.photo.location.map_debug.R

enum class DailyNotificationType(
    val day: Int,
    @param:StringRes val title: Int,
    @param:StringRes val description: Int,
    val isMorning: Boolean
) {

    KNOWLEDGE_1(
        day = 1,
        title = R.string.knowledge_title_1,
        description = R.string.knowledge_desc_1,
        isMorning = true
    ),

    KNOWLEDGE_2(
        day = 2,
        title = R.string.knowledge_title_2,
        description = R.string.knowledge_desc_2,
        isMorning = true
    ),

    KNOWLEDGE_3(
        day = 1,
        title = R.string.knowledge_title_3,
        description = R.string.knowledge_desc_3,
        isMorning = false
    ),

    KNOWLEDGE_4(
        day = 2,
        title = R.string.knowledge_title_4,
        description = R.string.knowledge_desc_4,
        isMorning = false
    ),

    KNOWLEDGE_5(
        day = 2,
        title = R.string.knowledge_title_5,
        description = R.string.knowledge_desc_5,
        isMorning = false
    ),

     KNOWLEDGE_6(
        day = 2,
        title = R.string.knowledge_title_6,
        description = R.string.knowledge_desc_6,
        isMorning = false
    ),

     KNOWLEDGE_7(
        day = 2,
        title = R.string.knowledge_title_7,
        description = R.string.knowledge_desc_7,
        isMorning = false
    ),

     KNOWLEDGE_8(
        day = 2,
        title = R.string.knowledge_title_8,
        description = R.string.knowledge_desc_8,
        isMorning = false
    ),

     KNOWLEDGE_9(
        day = 2,
        title = R.string.knowledge_title_9,
        description = R.string.knowledge_desc_9,
        isMorning = false
    ),

     KNOWLEDGE_10(
        day = 2,
        title = R.string.knowledge_title_10,
        description = R.string.knowledge_desc_10,
        isMorning = false
    ),

    KNOWLEDGE_11(
        day = 1,
        title = R.string.knowledge_title_11,
        description = R.string.knowledge_desc_11,
        isMorning = true
    ),

    KNOWLEDGE_12(
        day = 2,
        title = R.string.knowledge_title_12,
        description = R.string.knowledge_desc_12,
        isMorning = true
    ),

    KNOWLEDGE_13(
        day = 1,
        title = R.string.knowledge_title_13,
        description = R.string.knowledge_desc_13,
        isMorning = false
    ),

    KNOWLEDGE_14(
        day = 2,
        title = R.string.knowledge_title_14,
        description = R.string.knowledge_desc_14,
        isMorning = false
    ),

    KNOWLEDGE_15(
        day = 2,
        title = R.string.knowledge_title_15,
        description = R.string.knowledge_desc_15,
        isMorning = false
    ),

     KNOWLEDGE_16(
        day = 2,
        title = R.string.knowledge_title_16,
        description = R.string.knowledge_desc_16,
        isMorning = false
    ),

     KNOWLEDGE_17(
        day = 2,
        title = R.string.knowledge_title_17,
        description = R.string.knowledge_desc_17,
        isMorning = false
    ),

     KNOWLEDGE_18(
        day = 2,
        title = R.string.knowledge_title_18,
        description = R.string.knowledge_desc_18,
        isMorning = false
    ),

     KNOWLEDGE_19(
        day = 2,
        title = R.string.knowledge_title_19,
        description = R.string.knowledge_desc_19,
        isMorning = false
    ),

     KNOWLEDGE_20(
        day = 2,
        title = R.string.knowledge_title_20,
        description = R.string.knowledge_desc_20,
        isMorning = false
    );
}