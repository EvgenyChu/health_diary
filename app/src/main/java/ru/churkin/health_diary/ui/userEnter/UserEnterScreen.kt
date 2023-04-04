package ru.churkin.health_diary.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.churkin.health_diary.R
import ru.churkin.health_diary.ui.componentsUI.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserEnterScreen(
    vm: UserEnterViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit
) {
    val state: UserEnterState by vm.state.collectAsState()
    val focusManager = LocalFocusManager.current

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Log.e("UserEnterScreen", "${state.screen}")
        when (val screen = state.screen) {
            is UserEnterScreen.Loading -> Loading()
            is UserEnterScreen.UserEnterView -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.primary)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = stringResource(id = R.string.text_login),
                            color = MaterialTheme.colors.onPrimary,
                            style = MaterialTheme.typography.h1
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.info_login),
                            color = MaterialTheme.colors.onPrimary,
                            style = MaterialTheme.typography.h4
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colors.background,
                                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            )
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        UserField(
                            value = screen.name,
                            title = stringResource(id = R.string.user_name),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus(force = true) }
                            ),
                            imeAction = ImeAction.Done,
                            onValue = {
                                vm.updateName(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        UserField(
                            value = screen.age,
                            title = stringResource(id = R.string.user_age),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus(force = true) }
                            ),
                            imeAction = ImeAction.Done,
                            onValue = {
                                vm.updateAge(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        UserField(
                            value = screen.weight,
                            title = stringResource(id = R.string.user_weight),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus(force = true) }
                            ),
                            imeAction = ImeAction.Done,
                            onValue = {
                                vm.updateWeight(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Spacer(modifier = Modifier.weight(1f))
                        EnterButton(
                            text = R.string.button_add,
                            modifier = Modifier.padding(horizontal = 16.dp),
                        ) {
                            vm.saveUser()
                            onNavigateToMain()
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        EnterButton(
                            text = R.string.button_clear,
                            modifier = Modifier.padding(horizontal = 16.dp),
                        ) {
                            vm.clearScreen()
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}