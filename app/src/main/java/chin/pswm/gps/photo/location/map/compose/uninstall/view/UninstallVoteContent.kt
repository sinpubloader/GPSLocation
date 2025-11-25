package chin.pswm.gps.photo.location.map.compose.uninstall.view

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.activity.earthview.custom.BaseScreen
import chin.pswm.gps.photo.location.map.activity.earthview.custom.CenterRow
import chin.pswm.gps.photo.location.map.activity.earthview.custom.HeaderView
import chin.pswm.gps.photo.location.map.activity.earthview.custom.onClickNotRipple
import chin.pswm.gps.photo.location.map.activity.earthview.custom.rounded
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map_debug.R
import chin.pswm.gps.photo.location.map.compose.uninstall.view.UninstallVoteItem.Companion.items

@Immutable
enum class UninstallVoteItem(
    @StringRes val title: Int, val event: String
) {
    Vote1(
        R.string.i_no_longer_need_it, "uninstall_longer_need"
    ),
    Vote3(
        R.string.i_didn_t_understand_how_to_use_it, "uninstall_not_know_use"
    ),
    Vote4(
        R.string.it_wasn_t_working_as_expected, "uninstall_not_working"
    ),
    Vote5(
        R.string.i_found_a_better_alternative, "uninstall_found_better"
    ),
    Vote6(R.string.difficult_to_use, "uninstall_difficult_to_use"),
    Vote7(R.string.others, "uninstall_others");

    companion object {
        val items by lazy {
            mutableStateListOf<UninstallVoteItem>()
        }
    }
}

@Composable
fun UninstallVoteContent(
    onBack: () -> Unit = {}, onUninstall: () -> Unit = {}
) {
    BaseScreen(topBar = {
        HeaderView(
            title = R.string.survey_install, iconLeft = R.drawable.ic_back, onLeftIconClick = {
                onBack()
            })
    }, bottomBar = {
        Column{
            Text(
                text = stringResource(R.string.continue_submit),
                style = appFont(600, 14),
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
                    .fillMaxWidth(1f)
                    .rounded(8.dp)
                    .background(Color.Black)
                    .onClickNotRipple("uninstall") {
                        onUninstall()
                    }
                    .padding(vertical = 14.dp))

        }

    }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.vote_title),
                style = appFont(700, 18),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            UninstallVoteItem.entries.forEach { item ->
                val isSelected by remember {
                    derivedStateOf {
                        items.contains(item)
                    }
                }
                VoteItem(
                    item = item, isSelected = isSelected, onClick = {
                        if (items.contains(item)) {
                            items.remove(item)
                        } else {
                            items.add(item)
                        }
                    })
            }
        }
    }
}

@Composable
fun VoteItem(
    item: UninstallVoteItem, isSelected: Boolean, onClick: () -> Unit
) {
    CenterRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        itemSpacing = 8.dp
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = {
                onClick()
            },
            modifier = Modifier.size(24.dp)
        )

        Text(
            stringResource(item.title),
            style = appFont(600, 14),
            color = Color.Black,
            modifier = Modifier.weight(1f)
                .onClickNotRipple(item.event) {
                    onClick()
                }
        )
    }
}

@Preview
@Composable
private fun PreviewUninstallVoteContent() {
    UninstallVoteContent()
}