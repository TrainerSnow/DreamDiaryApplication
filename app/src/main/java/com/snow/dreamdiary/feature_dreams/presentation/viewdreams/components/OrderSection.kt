package com.snow.dreamdiary.feature_dreams.presentation.viewdreams.components;

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.util.OrderType
import com.snow.dreamdiary.feature_dreams.domain.util.DreamOrder

@Composable
fun OrderButtonGroup(
    order: DreamOrder,
    onOrderChange: (DreamOrder) -> Unit,
) {
    Column{
        Row {
            LabeledRadioButton(
                onClick = {
                    onOrderChange(DreamOrder.Created(order.orderType))
                },
                text = stringResource(id = R.string.order_created),
                selected = order is DreamOrder.Created
            )
            LabeledRadioButton(
                onClick = {
                    onOrderChange(DreamOrder.Dreamed(order.orderType))
                },
                text = stringResource(id = R.string.order_dreamed),
                selected = order is DreamOrder.Dreamed
            )
        }
        Row {
            LabeledRadioButton(
                onClick = {
                    onOrderChange(
                        if (order is DreamOrder.Dreamed){
                            DreamOrder.Dreamed(OrderType.Descending)
                        }else{
                            DreamOrder.Created(OrderType.Descending)
                        }
                    )
                },
                text = stringResource(id = R.string.order_descending),
                selected = order.orderType == OrderType.Descending
            )
            LabeledRadioButton(
                onClick = {
                    onOrderChange(
                        if (order is DreamOrder.Dreamed){
                            DreamOrder.Dreamed(OrderType.Ascending)
                        }else{
                            DreamOrder.Created(OrderType.Ascending)
                        }
                    )
                },
                text = stringResource(id = R.string.order_ascending),
                selected = order.orderType == OrderType.Ascending
            )
        }

    }
}

@Composable
fun CompleteOrderSection(
    order: DreamOrder,
    onOrderChange: (DreamOrder) -> Unit,
    onExpandClick: () -> Unit,
    isExpanded: Boolean
) {
    Row {
        AnimatedVisibility(
            modifier = Modifier
                .weight(4F),
            visible = isExpanded,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
        ) {
            OrderButtonGroup(
                order = order,
                onOrderChange = onOrderChange
            )
        }
        Spacer(modifier = Modifier.weight(1F))
        IconButton(
            onClick = onExpandClick
        ) {
            Icon(
                imageVector = Icons.Rounded.FilterList,
                contentDescription = stringResource(id = R.string.cd_expand_sort_menu)
            )
        }
    }

}