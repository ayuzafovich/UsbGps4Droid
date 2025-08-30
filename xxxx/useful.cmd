adb shell settings put global development_settings_enabled 1

adb shell appops set org.broeuschmeul.android.gps.usb.provider android:mock_location allow
adb shell appops get org.broeuschmeul.android.gps.usb.provider android:mock_location

adb tcpip 5555
adb connect 192.168.1.57:36857

adb root