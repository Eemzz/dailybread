package com.example.dailybread.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun DBTextField(label: String, keyboardOptions: KeyboardOptions, textState: MutableState<TextFieldValue>) {
    Column(Modifier.padding(5.dp)) {
        OutlinedTextField(
            shape = RoundedCornerShape(20),
            value = textState.value,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            label = { Text(text = label) },
            onValueChange = { textState.value = it }
        )

    }
}