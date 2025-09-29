package chin.pswm.gps.photo.location.map.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.earthview.custom.AppIcon
import chin.pswm.gps.photo.location.map.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.earthview.custom.CenterRow
import chin.pswm.gps.photo.location.map.earthview.custom.onClick
import chin.pswm.gps.photo.location.map.earthview.custom.rounded
import chin.pswm.gps.photo.location.map_debug.R

@Immutable
open class PermissionBottomSheetState {
    var isShow by mutableStateOf(false)
    val permissions = mutableStateListOf<PermissionType>()
    fun show(items: List<PermissionType>) {
        this.permissions.clear()
        this.permissions.addAll(items)
        this.isShow = true
    }

    fun hide() {
        permissions.clear()
        isShow = false
    }

    fun remove(permissionType: PermissionType) {
        this.permissions.remove(permissionType)
        this.isShow = this.permissions.isNotEmpty()
    }

    open fun onAllow(permissionType: PermissionType) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionBottomSheet(
    state: PermissionBottomSheetState,
) {
    if (state.isShow) {
        ModalBottomSheet(
            onDismissRequest = {
                state.hide()
            },
            dragHandle = {

            }
        ) {
            PermissionBottomSheetContent(
                state = state,
            )
        }
    }
}

@Composable
fun PermissionBottomSheetContent(
    state: PermissionBottomSheetState,
) {
    CenterColumn(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
            .padding(16.dp),
        itemSpacing = 16.dp
    ) {
        Box(Modifier.fillMaxWidth()) {
            Text(
                stringResource(R.string.permissions_required),
                style = appFont(600, 16),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            AppIcon(
                res = R.drawable.ic_close,
                color = colorBlack,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp)
                    .onClick("close_permission") {
                        state.hide()
                    }
            )
        }

        state.permissions.forEach { type ->
            CenterRow(
                Modifier.fillMaxWidth(),
                itemSpacing = 12.dp
            ) {
                AppImage(
                    res = type.icon,
                    modifier = Modifier.size(48.dp)
                )

                CenterColumn(Modifier.weight(1f)) {

                    Text(
                        stringResource(type.title),
                        style = appFont(600, 16),
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Text(
                        stringResource(type.body),
                        style = appFont(400, 12),
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Text(
                    stringResource(R.string.allow),
                    style = appFont(500, 14),
                    color = Color.Black,
                    modifier = Modifier
                        .rounded(12.dp)
                        .background(primary)
                        .onClick("allow_permisison_${type.ordinal}") {
                            state.onAllow(type)
                        }
                        .padding(vertical = 6.dp, horizontal = 10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPermissionBottomSheet() {
    PermissionBottomSheetContent(
        state = PermissionBottomSheetState().apply {
            show(PermissionType.entries)
        }
    )
}