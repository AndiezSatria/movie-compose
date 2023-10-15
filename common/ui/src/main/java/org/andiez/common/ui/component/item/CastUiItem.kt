package org.andiez.common.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.andiez.common.ui.theme.BackgroundPrimary
import org.andiez.common.ui.theme.ContentPrimary
import org.andiez.common.ui.theme.InactivePrimary
import org.andiez.common.util.CommonConstant
import org.andiez.presenter.model.CastItem

/**
 * Created by AndiezSatria on 15/10/2023.
 */

@Composable
fun CastUiItem(
    modifier: Modifier = Modifier,
    castItem: CastItem,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(160.dp)
            .width(100.dp)
            .background(BackgroundPrimary),
    ) {
        AsyncImage(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp),
            model = "${CommonConstant.IMG_W500}${castItem.img}",
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            castItem.character,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2.copy(
                color = ContentPrimary,
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            castItem.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2.copy(
                color = InactivePrimary,
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        )

    }
}