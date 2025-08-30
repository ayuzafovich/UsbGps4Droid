package org.broeuschmeul.android.gps.usb.provider.ui;

import static org.broeuschmeul.android.gps.usb.provider.driver.USBGpsProviderService.startGpsProvider;
import static org.broeuschmeul.android.gps.usb.provider.ui.GpsInfoActivity.DELAY_MILLISECONDS;

import android.app.Activity;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

public class TransparentActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startGpsProvider(this);
        if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(getIntent().getAction())) {
            // Create a Handler associated with the main Looper
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Finish the activity after the delay
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        if (!isFinishing() && !isDestroyed()) {
                            finish();
                        }
                    } else if (!isFinishing()) {
                        finish();
                    }
                }
            }, DELAY_MILLISECONDS);
        }
    }
}
