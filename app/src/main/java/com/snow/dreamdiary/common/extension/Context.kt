package com.snow.dreamdiary.common.extension;

import android.content.Context
import java.io.File

fun Context.databaseDir(name: String): File{
    return File(this.filesDir.path.removeSuffix("/files").plus("/databases"))
}