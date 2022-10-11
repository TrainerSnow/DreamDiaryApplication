package com.snow.dreamdiary.feature_dreams.presentation.addeditdream.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.snow.dreamdiary.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
fun DatePicker(
    dialogState: MaterialDialogState,
    onDateSelect: (LocalDate) -> Unit
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(LocalContext.current.getString(R.string.ok))
            negativeButton(LocalContext.current.getString(R.string.cancel))
        }
    ) {
        datepicker{
            onDateSelect(it)
        }
    }
}