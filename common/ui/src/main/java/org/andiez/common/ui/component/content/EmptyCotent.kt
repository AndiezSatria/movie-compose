package org.andiez.common.ui.component.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.andiez.common.ui.R
import org.andiez.common.ui.component.button.PrimaryRegularButton
import org.andiez.common.ui.theme.ContentPrimary

/**
 * Created by AndiezSatria on 15/10/2023.
 */

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
    isNotFound: Boolean,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_no_data),
                contentDescription = "",
                modifier = Modifier.height(50.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                stringResource(id = if (isNotFound) R.string.txt_empty_info else R.string.txt_not_found_info),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2.copy(
                    color = ContentPrimary,
                ),
            )
        }
    }
}

@Composable
fun EmptyFavoriteContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_no_data),
                contentDescription = "",
                modifier = Modifier.height(50.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                stringResource(id = R.string.txt_no_favorite_yet),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2.copy(
                    color = ContentPrimary,
                ),
            )
        }
    }
}