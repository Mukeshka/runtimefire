/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.ContentObserver
 *  android.media.AudioManager
 *  android.net.Uri
 *  android.os.Handler
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  java.lang.Object
 *  java.lang.String
 */
package com.unity3d.player;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

final class a {
    private final Context a;
    private final AudioManager b;
    private a c;

    public a(Context context) {
        this.a = context;
        this.b = (AudioManager)context.getSystemService("audio");
    }

    public final void a() {
        if (this.c != null) {
            this.a.getContentResolver().unregisterContentObserver((ContentObserver)this.c);
            this.c = null;
        }
    }

    public final void a(b b2) {
        a a2;
        Handler handler = new Handler();
        this.c = a2 = new a(handler, this.b, 3, b2);
        this.a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, (ContentObserver)this.c);
    }

    private final class a
    extends ContentObserver {
        private final b b;
        private final AudioManager c;
        private final int d;
        private int e;

        public a(Handler handler, AudioManager audioManager, int n2, b b2) {
            super(handler);
            this.c = audioManager;
            this.d = 3;
            this.b = b2;
            this.e = audioManager.getStreamVolume(this.d);
        }

        public final boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        public final void onChange(boolean bl, Uri uri) {
            int n2;
            AudioManager audioManager = this.c;
            if (audioManager != null && this.b != null && (n2 = audioManager.getStreamVolume(this.d)) != this.e) {
                this.e = n2;
                this.b.onAudioVolumeChanged(n2);
            }
        }
    }

    public static interface b {
        public void onAudioVolumeChanged(int var1);
    }

}

