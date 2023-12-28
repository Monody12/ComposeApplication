package com.example.composeapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeapplication.bean.ShiftInfo
import com.example.composeapplication.viewmodel.CheckTicketViewModel


@Composable
fun CheckTicketInfoShow(shiftInfo: ShiftInfo) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "班次名称：${shiftInfo.name}")
        Text(text = "线路名称：${shiftInfo.routeName}")
        Text(text = "发车日期：${shiftInfo.startDate}")
        Text(text = "发车时间：${shiftInfo.startTime}")
        Text(text = "流水班结束时间：${shiftInfo.startEndTime}")
        Text(text = "已售座位数：${shiftInfo.soldSeatNum}")
        Text(text = "可售座位数：${shiftInfo.saleSeatNum}")
        Text(text = "检票数：${shiftInfo.checkTicket}")
        Text(text = "站点信息：${shiftInfo.stationList}")
    }
}

@Composable
fun CheckTicketScreen() {
    val viewModel: CheckTicketViewModel = viewModel()
    val shiftInfo: MutableLiveData<MutableList<ShiftInfo>> = viewModel.shiftInfoLiveData
    val shiftInfoState = remember {
        mutableStateOf<List<ShiftInfo>>(emptyList())
    }
    // 观察 MutableLiveData 的变化，手动更新 State
    DisposableEffect(viewModel.shiftInfoLiveData.value) {
        shiftInfoState.value = viewModel.shiftInfoLiveData.value.orEmpty()
        onDispose {

        }
    }
    Column {
        Text(text = "检票信息")
        Button(
            onClick = {
                viewModel.getCheckTicket("2023-12-28", 1)
            }, modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "更新")
        }
        LazyColumn {
            items(shiftInfoState.value.size) { index ->
                CheckTicketInfoShow(shiftInfoState.value[index])
            }
        }
    }
}

@Preview
@Composable
fun CheckTicketInfoShowPreview() {
    val shiftInfo = ShiftInfo().apply {
        id = 1
        routeId = 1
        name = "班次名称"
        routeName = "线路名称"
        startDate = "发车日期"
        startTime = "发车事件"
        startEndTime = "流水班结束时间"
        soldSeatNum = 10
        saleSeatNum = 20
        checkTicket = 10
        stationList = emptyList()
    }
    CheckTicketInfoShow(shiftInfo)
}
