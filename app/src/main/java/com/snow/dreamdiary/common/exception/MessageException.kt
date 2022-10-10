package com.snow.dreamdiary.common.exception;

import androidx.annotation.StringRes
import java.lang.Exception

class MessageException(
    @StringRes val text: Int
): RuntimeException() {

}