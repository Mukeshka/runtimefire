/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.ConnectivityManager$NetworkCallback
 *  android.net.Network
 *  android.net.NetworkCapabilities
 *  android.net.NetworkInfo
 *  java.lang.Object
 *  java.lang.String
 */
package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

public class NetworkConnectivity
extends Activity {
    private final int a = 0;
    private final int b;
    private final int c;
    private int d;
    private ConnectivityManager e;
    private final ConnectivityManager.NetworkCallback f;

    public NetworkConnectivity(Context context) {
        int n2;
        this.b = n2 = 1;
        this.c = 2;
        this.d = 0;
        this.f = new ConnectivityManager.NetworkCallback(){

            public final void onAvailable(Network network) {
                super.onAvailable(network);
            }

            /*
             * Enabled aggressive block sorting
             */
            public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                NetworkConnectivity networkConnectivity;
                int n2;
                super.onCapabilitiesChanged(network, networkCapabilities);
                if (networkCapabilities.hasTransport(0)) {
                    networkConnectivity = NetworkConnectivity.this;
                    n2 = 1;
                } else {
                    networkConnectivity = NetworkConnectivity.this;
                    n2 = 2;
                }
                networkConnectivity.d = n2;
            }

            public final void onLost(Network network) {
                super.onLost(network);
                NetworkConnectivity.this.d = 0;
            }

            public final void onUnavailable() {
                super.onUnavailable();
                NetworkConnectivity.this.d = 0;
            }
        };
        this.e = (ConnectivityManager)context.getSystemService("connectivity");
        this.e.registerDefaultNetworkCallback(this.f);
        NetworkInfo networkInfo = this.e.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() != 0) {
                n2 = 2;
            }
            this.d = n2;
        }
    }

    public final int a() {
        return this.d;
    }

    public final void b() {
        this.e.unregisterNetworkCallback(this.f);
    }

}

