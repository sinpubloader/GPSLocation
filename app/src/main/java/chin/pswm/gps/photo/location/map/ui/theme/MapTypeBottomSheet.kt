package chin.pswm.gps.photo.location.map.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.earthview.custom.AppIcon
import chin.pswm.gps.photo.location.map.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.earthview.custom.BaseBottomSheet
import chin.pswm.gps.photo.location.map.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.earthview.custom.CenterRow
import chin.pswm.gps.photo.location.map.earthview.custom.onClick
import chin.pswm.gps.photo.location.map.earthview.custom.onClickNotRipple
import chin.pswm.gps.photo.location.map.earthview.custom.rounded
import chin.pswm.gps.photo.location.map_debug.R

@Immutable
open class MapTypeBottomSheetState {
    var isShow by mutableStateOf(false)
    var typeMap by mutableStateOf(TypeMap.Normal)

    open fun initBeforeShow() {

    }

    fun show() {
        initBeforeShow()
        this.isShow = true
    }

    fun hide() {
        this.isShow = false
    }

    open fun onSave(typeMap: TypeMap) {
        // Override in ViewModel
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapTypeBottomSheet(
    state: MapTypeBottomSheetState
) {
    if (state.isShow) {
        BaseBottomSheet(
            onDismiss = {
                state.hide()
            }
        ) {
            MapTypeBottomSheetContent(
                state = state
            )
        }
    }
}

@Composable
fun MapTypeBottomSheetContent(
    state: MapTypeBottomSheetState
) {
    CenterColumn(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {

        TypeMap.entries.forEach { type ->
            CenterRow(
                Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .fillMaxWidth()
                    .onClick("map_type_option") {
                        state.typeMap = type
                    }
                    .padding(vertical = 16.dp, horizontal = 12.dp),
                itemSpacing = 12.dp
            ) {

                AppImage(
                    res = type.icon,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(44.dp)
                        .rounded(4.dp)
                )

                Text(
                    text = stringResource(type.title),
                    style = appFont(600, 16),
                    modifier = Modifier.weight(1f)
                )

                if (state.typeMap == type) {
                    AppIcon(
                        res = R.drawable.ic_check,
                        modifier = Modifier.size(18.dp),
                        color = Color(0xFF096B6C)
                    )
                }
            }
        }


        Text(
            text = stringResource(R.string.save),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .rounded(8.dp)
                .background(primary)
                .onClickNotRipple("save") {
                    state.onSave(state.typeMap)
                    state.hide()
                }
                .padding(vertical = 12.dp),
        )
    }
}

@Preview
@Composable
private fun PreviewMapTypeBottomSheetContent() {
    MapTypeBottomSheetContent(
        state = MapTypeBottomSheetState()
    )
}