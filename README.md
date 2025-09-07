<img align="right" alt="App icon" src="app-icon.png" height="115px">

# Download APK
https://github.com/ayuzafovich/UsbGps4Droid/releases/download/release/UsbGps4Droid-v2.2.1_Julia.apk

# UsbGps4Droid - A USB GPS provider for Android 
UsbGps4Droid is a USB GPS provider application for the Android operating system,
providing GPS support for devices down to android 4.0

## Features from this fork
- Auto start service on USB device attachment.
- No more permission confirmations to use the USB device.
- Tested with the Geely Monjaro (2025 model).

## About
The application provides location updates to Android which allows devices without 
an internal GPS to still use applications which require GPS (Google Maps navigation).

I use this USB Gps with my Android Tablet Headunit, because the internal GPS does 
not work reliably. I have a [main controller](https://github.com/freshollie/AndroidHeadunitController/) 
which automatically starts this application's background service when my car starts

The application is designed to work with any SiRF USB GPS device, however it has been 
tested as working with [GlobalSat BU-353-S4](http://usglobalsat.com/p-688-bu-353-s4.aspx) and
[a device based on the UBLOX LEA-M8P chip](../../issues/7).

## Features
- Android 6.0+ Permission handling
- Ability to pick which USB device is the GPS
- Automatic start on reboot
- User interface with readings from the USB GPS and a log showing NMEA data coming from the GPS
- Abilty to sync android device time with GPS time (Requires root)
- Support for any SiRF USB GPS device

## Usage

### Connecting a USB GPS to your device
- A USB GPS device can connect to an Android device's charging port, with a USB OTG adapter, if your device supports 
[USB OTG](https://en.wikipedia.org/wiki/USB_On-The-Go)

- If your device has normal USB ports (Raspberry PI, computer...) then an OTG adapter is not required.

### Disclaimer
This app has only been tested on 2 of my devices, running Android 5.1 and 6.0. Any issues with 
this app on your device, please create a  [github issue](../../issues) and I will resolve the 
problem as soon as possible.

### Service behaviour 
The application's background service can be set to start when the device turns on. 
Currently the service does not automatically start when the GPS device is plugged 
into the Android device due to more unwanted usb popups.

For now the background service can be manually started with a start service intent.

```java
Intent intent = new Intent();
intent.setComponent(
	new Component(
		"org.broeuschmeul.android.gps.usb.provider",
		"org.broeuschmeul.android.gps.usb.provider.driver.USBGpsProviderService"
	)
)
intent.setAction("org.broeuschmeul.android.gps.usb.provider.action.START_GPS_PROVIDER")
```

Or via a shell command as root.

```bash
am startservice -a org.broeuschmeul.android.gps.usb.provider.action.START_GPS_PROVIDER -n org.broeuschmeul.android.gps.usb.provider/.driver.USBGpsProviderService
```

The background service will automatically close itself when the USB device is disconnected for too long.

## Contributing

Any contributions welcome. Please fork this repository and create a pull request notifying your changes and why.

## Screenshots

### Landscape tablet
<p align="center">
    <img src="fastlane/metadata/android/en-US/images/sevenInchScreenshots/2.png" align="center" alt="Main interface marshmallow" width="800"/>
</p>

#### Android 3.1 interface
<p align="center">
    <img src="fastlane/metadata/android/en-US/images/sevenInchScreenshots/3.png" align="center" alt="Main interface honeycomb" width="800"/>
</p>

### Portrait
<p align="center">
    <img src="fastlane/metadata/android/en-US/images/sevenInchScreenshots/1.png" align="center" alt="Main interface portait" width="400"/>
</p>

### Device selection
<p align="center">
    <img src="fastlane/metadata/android/en-US/images/sevenInchScreenshots/4.png" align="center" alt="Device choice settings" width="800"/>
</p>

## Credits
Originally written by Herbert von Broeuschmeul, and I have taken over maintaining this 
Project (Which was originally written in 2011). You can find his orginal project at 
the top of this fork.

The Herbert's unmaintained version does not work on the newer android operating systems, 
and has several bugs with the main usb algorithm.

## License

`GPL v3`
