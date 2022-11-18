package com.example.dailybread.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DBTextField(label: String, keyboardOptions: KeyboardOptions,textState:
MutableState<TextFieldValue>, visualTransformation: VisualTransformation = VisualTransformation.None ) {
    Column(Modifier.padding(top = 5.dp)) {
        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            shape = RoundedCornerShape(20),
            value = textState.value,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()}),
            label = { Text(text = label) },
            onValueChange = { textState.value = it },
            visualTransformation = visualTransformation
        )

    }
}
