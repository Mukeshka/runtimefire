/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.media.AudioManager
 *  java.lang.Enum
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.String
 */
package com.unity3d.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.unity3d.player.g;

public class HFPStatus {
    private Context a;
    private BroadcastReceiver b = null;
    private Intent c = null;
    private boolean d = false;
    private AudioManager e = null;
    private int f = a.a;

    public HFPStatus(Context context) {
        this.a = context;
        this.e = (AudioManager)this.a.getSystemService("audio");
        this.initHFPStatusJni();
    }

    private final native void deinitHFPStatusJni();

    private final native void initHFPStatusJni();

    public final void a() {
        this.deinitHFPStatusJni();
    }

    protected boolean getHFPStat() {
        return this.f == a.b;
    }

    protected void requestHFPStat() {
        this.b = new BroadcastReceiver(){

            public void onReceive(Context context, Intent intent) {
                int n2 = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                if (n2 != -1) {
                    if (n2 != 0) {
                        if (n2 != 1) {
                            if (n2 != 2) {
                                return;
                            }
                            if (HFPStatus.this.f == a.b) {
                                HFPStatus.this.d = true;
                                return;
                            }
                            HFPStatus.this.f = a.c;
                            return;
                        }
                        HFPStatus.this.f = a.b;
                        if (!HFPStatus.this.d) {
                            HFPStatus.this.e.stopBluetoothSco();
                            return;
                        }
                        HFPStatus.this.e.setMode(3);
                        return;
                    }
                    if (HFPStatus.this.d) {
                        HFPStatus.this.e.setMode(0);
                    }
                    HFPStatus.this.d = false;
                }
            }
        };
        this.c = this.a.registerReceiver(this.b, new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        try {
            this.e.startBluetoothSco();
            return;
        }
        catch (NullPointerException nullPointerException) {
            g.Log(5, "startBluetoothSco() failed. no bluetooth device connected.");
            return;
        }
    }

    static final class a
    extends Enum {
        public static final /* enum */ int a = 1;
        public static final /* enum */ int b = 2;
        public static final /* enum */ int c = 3;
        private static final /* synthetic */ int[] d;

        static {
            int[] arrn = new int[]{a, b, c};
            d = arrn;
        }
    }

}

