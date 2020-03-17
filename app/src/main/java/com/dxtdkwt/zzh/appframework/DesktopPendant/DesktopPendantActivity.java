package com.dxtdkwt.zzh.appframework.DesktopPendant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dxtdkwt.zzh.appframework.R;

public class DesktopPendantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desktop_pendant_activity);

        if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
            ShortcutInfoCompat shortcut = new ShortcutInfoCompat.Builder(this, "id1")
                    .setShortLabel("Website")
                    .setLongLabel("Open the website")
                    .setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher_background))
                    .setIntent(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.mysite.example.com/")))
                    .build();

            Intent pinnedShortcutCallbackIntent = ShortcutManagerCompat.createShortcutResultIntent(this, shortcut);
            setResult(RESULT_OK, pinnedShortcutCallbackIntent);
            finish();
        }
    }
}
