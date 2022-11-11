package com.snow.dreamdiary.common.presentation.components;

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.domain.model.Dream

@Composable
fun DreamPreviewCard(
    dream: Dream,
    onClick: () -> Unit = {  },
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    strokeColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    maxLetterCount: Int = 280
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val dayCreated = TimeUtil.millisToDayNum(TimeUtil.dayRangeFromMillis(dream.dreamtAt).first)
    val dayNow = TimeUtil.millisToDayNum(TimeUtil.thisDayStartInMillis())
    val dif = dayNow - dayCreated
    val effectiveDescription = if (expanded) dream.description else
        if (dream.description.length <= maxLetterCount) dream.description
        else dream.description.slice(IntRange(0, maxLetterCount)).plus("...")

    Box(
        modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = strokeColor
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .clip(shape = RoundedCornerShape(24.dp))
            .clickable { onClick() }
            .background(backgroundColor)
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .animateContentSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column() {
            Text(
                text = "$dif ${stringResource(id = R.string.days_ago)}",
                style = MaterialTheme.typography.bodySmall,
                color = textColor
            )
            Text(
                text = "${stringResource(id = R.string.dream_from)} ${
                    TimeFormatUtil.getMillisDayFormatted(
                        dream.dreamtAt
                    )
                }",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )
            Text(
                text = effectiveDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )
            Text(
                modifier = Modifier
                    .clickable {
                        expanded = !expanded
                    },
                text = stringResource(
                    id = if (expanded)
                        R.string.show_less
                    else
                        R.string.show_more
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DreamPreviewCardPreview() {
    DreamPreviewCard(
        dream = Dream(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. ",
            listOf("Niklas", "Jona"),
            listOf("Cool", "Sad"),
            listOf("GGE", "Zuhause"),
            4,
            System.currentTimeMillis(),
            System.currentTimeMillis()
        )
    )
}