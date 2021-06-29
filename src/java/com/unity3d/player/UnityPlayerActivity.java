/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.os.Bundle
 *  android.os.Process
 *  android.view.InputEvent
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  java.lang.String
 */
package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Process;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.unity3d.player.IUnityPlayerLifecycleEvents;
import com.unity3d.player.UnityPlayer;

public class UnityPlayerActivity
extends Activity
implements IUnityPlayerLifecycleEvents {
    protected UnityPlayer mUnityPlayer;

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 2) {
            return this.mUnityPlayer.injectEvent((InputEvent)keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mUnityPlayer.configurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        this.requestWindowFeature(1);
        super.onCreate(bundle);
        String string2 = this.updateUnityCommandLineArguments(this.getIntent().getStringExtra("unity"));
        this.getIntent().putExtra("unity", string2);
        this.mUnityPlayer = new UnityPlayer((Context)this, this);
        this.setContentView((View)this.mUnityPlayer);
        this.mUnityPlayer.requestFocus();
    }

    protected void onDestroy() {
        this.mUnityPlayer.destroy();
        super.onDestroy();
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return this.mUnityPlayer.injectEvent((InputEvent)motionEvent);
    }

    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        return this.mUnityPlayer.injectEvent((InputEvent)keyEvent);
    }

    public boolean onKeyUp(int n2, KeyEvent keyEvent) {
        return this.mUnityPlayer.injectEvent((InputEvent)keyEvent);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mUnityPlayer.lowMemory();
    }

    protected void onNewIntent(Intent intent) {
        this.setIntent(intent);
        this.mUnityPlayer.newIntent(intent);
    }

    protected void onPause() {
        super.onPause();
        this.mUnityPlayer.pause();
    }

    protected void onResume() {
        super.onResume();
        this.mUnityPlayer.resume();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mUnityPlayer.injectEvent((InputEvent)motionEvent);
    }

    public void onTrimMemory(int n2) {
        super.onTrimMemory(n2);
        if (n2 == 15) {
            this.mUnityPlayer.lowMemory();
        }
    }

    @Override
    public void onUnityPlayerQuitted() {
        Process.killProcess((int)Process.myPid());
    }

    @Override
    public void onUnityPlayerUnloaded() {
        this.moveTaskToBack(true);
    }

    public void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        this.mUnityPlayer.windowFocusChanged(bl);
    }

    protected String updateUnityCommandLineArguments(String string2) {
        return string2;
    }
}

