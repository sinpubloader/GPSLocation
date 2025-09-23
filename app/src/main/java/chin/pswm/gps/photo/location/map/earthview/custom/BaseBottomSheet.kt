package chin.pswm.gps.photo.location.map.earthview.custom

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheet(
    isShow: Boolean = true,
    onDismiss: () -> Unit = {},
    properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    ),
    containerColor: Color = Color.White,
    dragHandle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    if (isShow) {
        ModalBottomSheet(
            properties = properties,
            shape = RoundedCornerShape(topEnd = 28.dp, topStart = 28.dp),
            containerColor = containerColor,
            contentColor = containerColor,
            onDismissRequest = {
                onDismiss()
            },
            sheetState = sheetState,
            dragHandle = {
                dragHandle()
            },
            modifier = Modifier.statusBarsPadding()
        ) {
            content()
        }
    }
}