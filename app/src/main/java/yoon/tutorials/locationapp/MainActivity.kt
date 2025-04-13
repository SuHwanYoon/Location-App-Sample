package yoon.tutorials.locationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import yoon.tutorials.locationapp.ui.theme.LocationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // MyLocationApp() 함수를 호출하여 위치 권한을 확인하는 UI를 표시합니다.
                    MyLocationApp()
                }
            }
        }
    }
}

@Composable
fun MyLocationApp() {
    // LocalContext는 현재 Compose UI의 컨텍스트를 가져오는 데 사용됩니다.
    // LocalContext는 Compose UI에서 현재 컨텍스트를 가져오는 데 사용되는 Compose의 기능입니다.
    // MainActivity에서 setContent 블록 내에서 호출되므로, 현재 Activity의 컨텍스트를 가져옵니다.
    val appLocationContext = LocalContext.current
    // LocationUtils 인스턴스 생성
    val locationUtils = LocationUtils(locationContext = appLocationContext)
    // locationDisplay() 함수 호출
    locationDisplay(locationUtils = locationUtils, locationContext = appLocationContext)
}