package ru.churkin.health_diary.ui.componentsUI

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.churkin.health_diary.R
import ru.churkin.health_diary.helpers.formatDateForUser
import ru.churkin.health_diary.modelData.Diary

@Composable
fun DiaryCard(
    diary: Diary,
    modifier: Modifier = Modifier
) {
    var isExpended by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable { isExpended = !isExpended }
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row() {
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_heart_broken_24),
                    tint = MaterialTheme.colors.secondary,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = diary.date.formatDateForUser(),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            /*if (isExpended) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp),
                    color = MaterialTheme.colors.secondary
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${stringResource(id = R.string.SYS)} ${diary.topPressure}, ${
                            stringResource(
                                id = R.string.mmHg
                            )
                        }",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp),
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = stringResource(id = R.string.SYS_full_text),
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "${stringResource(id = R.string.DIA)} ${diary.bottomPressure}, ${
                            stringResource(
                                id = R.string.mmHg
                            )
                        }",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp),
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = stringResource(id = R.string.SYS_full_text),
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }*/
        }
    }
}