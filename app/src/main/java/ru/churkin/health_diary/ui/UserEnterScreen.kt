package ru.churkin.health_diary.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.churkin.health_diary.R
import ru.churkin.health_diary.ui.componentsUI.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserEnterScreen(
    vm: UserEnterViewModel = viewModel()
) {
    val state: UserEnterState by vm.state.collectAsState()

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
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(24.dp))
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
                            modifier = Modifier.padding(horizontal = 16.dp),
                            title = stringResource(id = R.string.user_name),
                            onValue = {
                                vm.updateName(it)
                            }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}