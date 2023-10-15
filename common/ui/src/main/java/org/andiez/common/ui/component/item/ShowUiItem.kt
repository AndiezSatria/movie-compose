package org.andiez.common.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.andiez.common.ui.theme.BackgroundPrimary
import org.andiez.presenter.model.ShowItem
import coil.compose.AsyncImage
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import org.andiez.common.ui.theme.ContentFourth
import org.andiez.common.ui.theme.ContentPrimary
import org.andiez.common.ui.theme.InactivePrimary
import org.andiez.common.ui.theme.MutePrimary
import org.andiez.common.ui.theme.SubmissionComposeTheme
import org.andiez.common.util.CommonConstant

/**
 * Created by AndiezSatria on 14/10/2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowUiItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    showItem: ShowItem,
) {
    Card(
        modifier = modifier
            .height(90.dp)
            .fillMaxWidth()
            .background(BackgroundPrimary),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.small)
                .background(BackgroundPrimary),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp),
                model = "${CommonConstant.IMG_W500}${showItem.img}",
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    showItem.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2.copy(
                        color = ContentPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    RatingBar(
                        value = showItem.voteAverage.toFloat() / 2f,
                        style = RatingBarStyle.Fill(),
                        isIndicator = true,
                        size = 12.dp,
                        spaceBetween = 2.dp,
                        onValueChange = {},
                        onRatingChanged = {},
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "${showItem.voteAverage}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.caption.copy(
                            color = InactivePrimary,
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowItemPreview() {
    SubmissionComposeTheme {
        ShowUiItem(showItem = ShowItem(
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            voteAverage = 7.9,
        ), onClick = {})
    }
}