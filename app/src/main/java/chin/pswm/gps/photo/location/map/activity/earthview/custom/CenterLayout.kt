package chin.pswm.gps.photo.location.map.activity.earthview.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
inline fun CenterBox(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) { content() }
}

@Composable
inline fun CenterColumn(
    modifier: Modifier = Modifier,
    itemSpacing: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(itemSpacing, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
inline fun CenterRow(
    modifier: Modifier = Modifier,
    itemSpacing: Dp = 0.dp,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(itemSpacing, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp),
    cardColor: Color = Color.White,
    elevation: Dp = 2.dp,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
        modifier = modifier
    ) {
        content()
    }
}