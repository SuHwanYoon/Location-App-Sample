package yoon.tutorials.locationapp

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

// LocationUtils는 위치 권한을 확인하는 유틸리티 클래스입니다.
// 이 클래스는 위치 권한이 있는지 확인하는 메서드를 제공합니다.
// LocationUtils는 Context를 매개변수로 받아서 위치 권한을 확인합니다.
// Context는 Android 애플리케이션의 현재 상태를 나타내는 객체입니다.

class LocationUtils(val locationContext: Context) {


    // hasLocationPermission()는 ACCESS_FINE_LOCATION과 ACCESS_COARSE_LOCATION 권한을 확인합니다.
    // 두 권한 모두 부여되어야 true를 반환합니다.
    // 만약 두 권한 중 하나라도 부여되지 않았다면 false를 반환합니다.
    // ContextComapt는 AndroidX 라이브러리의 일부로, Android의 다양한 기능을 사용할 수 있도록 도와주는 유틸리티 클래스
    // checkSelfPermission()은 정수(Int) 값을 반환합니다.
    //PackageManager에는 두 가지 주요 상수가 정의되어 있습니다:
    //PERMISSION_GRANTED = 0
    //PERMISSION_DENIED = -1
    // 0 == 0 -> true
    // -1 == 0 -> false
    /**
     * Checks if the app has location permissions.
     *
     * @param context The context of the current activity.
     * @return True if the app has location permissions, false otherwise.
     */
    fun hasLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}