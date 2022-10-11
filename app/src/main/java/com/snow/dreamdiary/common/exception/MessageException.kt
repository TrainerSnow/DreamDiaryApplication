package com.snow.dreamdiary.common.exception

import androidx.annotation.StringRes

class MessageException(
    @StringRes val text: Int
) : RuntimeException()