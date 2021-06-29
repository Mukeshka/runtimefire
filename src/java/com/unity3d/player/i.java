/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.os.Bundle
 *  java.lang.String
 */
package com.unity3d.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public final class i
extends Fragment {
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String[] arrstring = new String[]{this.getArguments().getString("PermissionNames")};
        this.requestPermissions(arrstring, 96489);
    }

    public final void onRequestPermissionsResult(int n2, String[] arrstring, int[] arrn) {
        if (n2 != 96489) {
            return;
        }
        if (arrstring.length == 0) {
            String[] arrstring2 = new String[]{this.getArguments().getString("PermissionNames")};
            this.requestPermissions(arrstring2, 96489);
            return;
        }
        FragmentTransaction fragmentTransaction = this.getActivity().getFragmentManager().beginTransaction();
        fragmentTransaction.remove((Fragment)this);
        fragmentTransaction.commit();
    }
}

