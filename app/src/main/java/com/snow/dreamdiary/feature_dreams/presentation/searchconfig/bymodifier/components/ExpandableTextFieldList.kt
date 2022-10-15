package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier.components

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun ExpandableTextFieldList(
    values: Set<String>,
    onValueChange: (Int, String) -> Unit
) {

    values.forEachIndexed{index, it ->
        TextField(
            value = it,
            onValueChange = { onValueChange(index, it) }
        )
    }

    TextField(
        value = "",
        onValueChange = { onValueChange(values.size, it) }
    )

}