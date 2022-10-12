package com.snow.dreamdiary.feature_dreams.presentation.viewdreams.components;

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.util.OrderType
import com.snow.dreamdiary.feature_dreams.domain.util.DreamOrder

@Composable
fun OrderSection(
    order: DreamOrder,
    onOrderChange: (DreamOrder) -> Unit
) {
    Column {
        Row{
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