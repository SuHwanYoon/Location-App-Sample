package yoon.tutorials.locationapp

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat

/**
 * Displays a button to check location permissions.
 *
 * @param locationUtils An instance of [LocationUtils] to check location permissions.
 * @param cㅣontext The context of the current activity.
 *
 * This function creates a UI with a button that checks for location permissions when clicked.
 * If the permissions are granted, it handles the case accordingly. If not, it shows a rationale
 * for requesting the permissions.
 */
// locationDisplay()는 위치 권한을 확인하는 버튼을 표시합니다.
// 이 함수는 LocationUtils의 인스턴스와 현재 활동의 컨텍스트를 매개변수로 받습니다.
// 이 함수는 클릭 시 위치 권한을 확인하는 버튼이 있는 UI를 생성합니다.
// 권한이 부여된 경우 해당 케이스를 처리합니다. 그렇지 않으면 권한 요청을 위한 합리적인 이유를 표시합니다.
// 이 함수는 Compose UI를 사용하여 버튼을 생성합니다.
@Composable
fun locationDisplay(locationUtils: LocationUtils, locationContext: Context) {

    // remeberLauncherForActivityResult는 권한 요청을 처리하는 데 사용됩니다.
    // contract는 어떤 종류의 작업을 수행할 것인지를 정의합니다.
    // ActivityResultContracts.RequestMultiplePermissions()는 여러 권한을 요청하는 계약입니다.
    // onResult는 권한 요청의 결과를 처리하는 콜백입니다.
    // permissions는 요청한 권한의 결과를 포함하는 맵입니다.
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            // location access permission이 부여된경우
            if (permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {

            } else {
                // location access permission이 부여되지 않은 경우
                // rationaleRequired는 사용자가 권한 요청을 거부했는지 여부를 확인
                // ActivityCompat.shouldShowRequestPermissionRationale()는 사용자가 이전에 권한 요청을 거부했는지 여부를 확인합니다.
                val rationaleRequired: Boolean =
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        locationContext as MainActivity,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) || ActivityCompat.shouldShowRequestPermissionRationale(
                        locationContext,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )

                if (rationaleRequired) {
                    //사용자가 이전에 권한 요청을 명시적으로 거부했을 때
                    //앱이 권한을 다시 요청하기 전에 사용자에게 왜 이 권한이 필요한지 설명해야 하는 상황
                    // 3.5초 동안 Toast 표시
                    Toast.makeText(
                        locationContext,
                        "Location permission is required to access location.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    // setting에서 권한이 dont allow로 설정되어 있는 경우
                    // 앱에서 직접 설정을 변경할수가 없는 상황
                    //사용자가 "다시 묻지 않음"을 선택하고 권한을 거부해서 앱에서 권한을 요청할 수 없는 상황
                    // 따라서 사용자가 앱 설정으로 이동하여 권한을 수동으로 활성화해야 함
                    Toast.makeText(
                        locationContext,
                        "Location permission is denied. Please enable it in settings.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    )

    // UI가 화면에 가득차고 가로 세로 정렬을 중앙으로 설정
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Location Permission Check")
        // 여백을 추가
        Spacer(modifier = Modifier.height(16.dp))
        // 위치 권한을 확인하는 버튼
        Button(onClick = {
            if (locationUtils.hasLocationPermission(locationContext)) {
                // 위치권한이 이미 가지고 있는 경우

            } else {
                // 위치권한을 가지고 있지 않은 경우
                // 권한 요청을 위한 launcher를 실행합니다.
                // requestPermissionLauncher는 권한 요청을 처리하는 데 사용됩니다.
                requestPermissionLauncher.launch(
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) {
            Text(text = "Check Location Permission")
        }
    }
}