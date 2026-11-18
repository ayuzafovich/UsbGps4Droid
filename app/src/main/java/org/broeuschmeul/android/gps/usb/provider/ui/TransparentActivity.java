package org.broeuschmeul.android.gps.usb.provider.ui;

import static org.broeuschmeul.android.gps.usb.provider.driver.USBGpsProviderService.startGpsProvider;

import android.app.Activity;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.preference.PreferenceManager;

import org.broeuschmeul.android.gps.usb.provider.driver.USBGpsProviderService;

/**
 * Started by the system when a matching USB device is attached: USB_DEVICE_ATTACHED is only
 * ever delivered to an activity, never to a broadcast receiver. This activity has no window of
 * its own — the theme is floating, translucent, without background, preview or animation — so
 * nothing is drawn. Its only job is to start the provider service, which Android 12+ allows
 * only while the app is in the foreground.
 */
public class TransparentActivity extends Activity {
    private static final long FINISH_DELAY_MS = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean usbAttached =
                UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(getIntent().getAction());
        if (!usbAttached || shouldStartOnUsb()) {
            startGpsProvider(this);
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finishAndRemoveTask();
            }
        }, usbAttached ? FINISH_DELAY_MS : 0);
    }

    private boolean shouldStartOnUsb() {
        return PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(USBGpsProviderService.PREF_START_ON_USB, true);
    }
}
