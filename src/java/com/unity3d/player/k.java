/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Point
 *  android.graphics.Rect
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.text.Editable
 *  android.text.InputFilter
 *  android.text.InputFilter$LengthFilter
 *  android.text.TextWatcher
 *  android.view.Display
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnFocusChangeListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.view.inputmethod.InputMethodManager
 *  android.view.inputmethod.InputMethodSubtype
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 */
package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unity3d.player.UnityPlayer;

public final class k
extends Dialog
implements TextWatcher,
View.OnClickListener {
    private static int c = 1627389952;
    private static int d = -1;
    private Context a = null;
    private UnityPlayer b = null;
    private int e;

    public k(Context context, UnityPlayer unityPlayer, String string2, int n2, boolean bl, boolean bl2, boolean bl3, String string3, int n3, boolean bl4) {
        super(context);
        this.a = context;
        this.b = unityPlayer;
        Window window = this.getWindow();
        window.requestFeature(1);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = 80;
        layoutParams.x = 0;
        layoutParams.y = 0;
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        final View view = this.createSoftInputView();
        this.setContentView(view);
        window.setLayout(-1, -2);
        window.clearFlags(2);
        window.clearFlags(134217728);
        window.clearFlags(67108864);
        EditText editText = (EditText)this.findViewById(1057292289);
        Button button = (Button)this.findViewById(1057292290);
        this.a(editText, string2, n2, bl, bl2, bl3, string3, n3);
        button.setOnClickListener((View.OnClickListener)this);
        this.e = editText.getCurrentTextColor();
        this.a(bl4);
        this.b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

            public final void onGlobalLayout() {
                if (view.isShown()) {
                    Rect rect = new Rect();
                    k.this.b.getWindowVisibleDisplayFrame(rect);
                    int[] arrn = new int[2];
                    k.this.b.getLocationOnScreen(arrn);
                    Point point = new Point(rect.left - arrn[0], rect.height() - view.getHeight());
                    Point point2 = new Point();
                    k.this.getWindow().getWindowManager().getDefaultDisplay().getSize(point2);
                    int n2 = k.this.b.getHeight() - point2.y;
                    int n3 = k.this.b.getHeight() - point.y;
                    if (n3 != n2 + view.getHeight()) {
                        k.this.b.reportSoftInputIsVisible(true);
                    } else {
                        k.this.b.reportSoftInputIsVisible(false);
                    }
                    Rect rect2 = new Rect(point.x, point.y, view.getWidth(), n3);
                    k.this.b.reportSoftInputArea(rect2);
                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            public final void onFocusChange(View view, boolean bl) {
                if (bl) {
                    k.this.getWindow().setSoftInputMode(5);
                }
            }
        });
        editText.requestFocus();
    }

    private static int a(int n2, boolean bl, boolean bl2, boolean bl3) {
        int n3 = bl ? 32768 : 524288;
        int n4 = bl2 ? 131072 : 0;
        int n5 = n3 | n4;
        int n6 = 0;
        if (bl3) {
            n6 = 128;
        }
        int n7 = n5 | n6;
        if (n2 >= 0) {
            if (n2 > 11) {
                return n7;
            }
            int[] arrn = new int[]{1, 16385, 12290, 17, 2, 3, 8289, 33, 1, 16417, 17, 8194};
            if ((2 & arrn[n2]) != 0) {
                return arrn[n2];
            }
            return n7 | arrn[n2];
        }
        return n7;
    }

    private void a(EditText editText, String string2, int n2, boolean bl, boolean bl2, boolean bl3, String string3, int n3) {
        editText.setImeOptions(6);
        editText.setText((CharSequence)string2);
        editText.setHint((CharSequence)string3);
        editText.setHintTextColor(c);
        editText.setInputType(k.a(n2, bl, bl2, bl3));
        editText.setImeOptions(33554432);
        if (n3 > 0) {
            InputFilter[] arrinputFilter = new InputFilter[]{new InputFilter.LengthFilter(n3)};
            editText.setFilters(arrinputFilter);
        }
        editText.addTextChangedListener((TextWatcher)this);
        editText.setSelection(editText.getText().length());
        editText.setClickable(true);
    }

    private void a(String string2, boolean bl) {
        ((EditText)this.findViewById(1057292289)).setSelection(0, 0);
        this.b.reportSoftInputStr(string2, 1, bl);
    }

    private String b() {
        EditText editText = (EditText)this.findViewById(1057292289);
        if (editText == null) {
            return null;
        }
        return editText.getText().toString().trim();
    }

    public final String a() {
        InputMethodSubtype inputMethodSubtype = ((InputMethodManager)this.a.getSystemService("input_method")).getCurrentInputMethodSubtype();
        if (inputMethodSubtype == null) {
            return null;
        }
        String string2 = inputMethodSubtype.getLocale();
        if (string2 != null && !string2.equals((Object)"")) {
            return string2;
        }
        String string3 = inputMethodSubtype.getMode();
        String string4 = inputMethodSubtype.getExtraValue();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string3);
        stringBuilder.append(" ");
        stringBuilder.append(string4);
        return stringBuilder.toString();
    }

    public final void a(int n2) {
        EditText editText = (EditText)this.findViewById(1057292289);
        if (editText != null) {
            if (n2 > 0) {
                InputFilter[] arrinputFilter = new InputFilter[]{new InputFilter.LengthFilter(n2)};
                editText.setFilters(arrinputFilter);
                return;
            }
            editText.setFilters(new InputFilter[0]);
        }
    }

    public final void a(int n2, int n3) {
        int n4;
        int n5;
        EditText editText = (EditText)this.findViewById(1057292289);
        if (editText != null && (n5 = editText.getText().length()) >= (n4 = n3 + n2)) {
            editText.setSelection(n2, n4);
        }
    }

    public final void a(String string2) {
        EditText editText = (EditText)this.findViewById(1057292289);
        if (editText != null) {
            editText.setText((CharSequence)string2);
            editText.setSelection(string2.length());
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void a(boolean bl) {
        int n2;
        EditText editText = (EditText)this.findViewById(1057292289);
        Button button = (Button)this.findViewById(1057292290);
        View view = this.findViewById(1057292291);
        if (bl) {
            n2 = 0;
            editText.setBackgroundColor(0);
            editText.setTextColor(0);
            editText.setCursorVisible(false);
            button.setClickable(false);
            button.setTextColor(0);
        } else {
            editText.setBackgroundColor(d);
            editText.setTextColor(this.e);
            editText.setCursorVisible(true);
            button.setClickable(true);
            button.setTextColor(this.e);
            n2 = d;
        }
        view.setBackgroundColor(n2);
    }

    public final void afterTextChanged(Editable editable) {
        this.b.reportSoftInputStr(editable.toString(), 0, false);
    }

    public final void beforeTextChanged(CharSequence charSequence, int n2, int n3, int n4) {
    }

    protected final View createSoftInputView() {
        RelativeLayout relativeLayout = new RelativeLayout(this.a);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(d);
        relativeLayout.setId(1057292291);
        EditText editText = new EditText(this.a){

            public final boolean onKeyPreIme(int n2, KeyEvent keyEvent) {
                if (n2 == 4) {
                    k k2 = k.this;
                    k2.a(k2.b(), true);
                    return true;
                }
                if (n2 == 84) {
                    return true;
                }
                return super.onKeyPreIme(n2, keyEvent);
            }

            protected final void onSelectionChanged(int n2, int n3) {
                k.this.b.reportSoftInputSelection(n2, n3 - n2);
            }

            public final void onWindowFocusChanged(boolean bl) {
                super.onWindowFocusChanged(bl);
                if (bl) {
                    ((InputMethodManager)k.this.a.getSystemService("input_method")).showSoftInput((View)this, 0);
                }
            }
        };
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, 1057292290);
        editText.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        editText.setId(1057292289);
        relativeLayout.addView((View)editText);
        Button button = new Button(this.a);
        button.setText(this.a.getResources().getIdentifier("ok", "string", "android"));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        button.setLayoutParams((ViewGroup.LayoutParams)layoutParams2);
        button.setId(1057292290);
        button.setBackgroundColor(0);
        relativeLayout.addView((View)button);
        ((EditText)relativeLayout.findViewById(1057292289)).setOnEditorActionListener(new TextView.OnEditorActionListener(){

            public final boolean onEditorAction(TextView textView, int n2, KeyEvent keyEvent) {
                if (n2 == 6) {
                    k k2 = k.this;
                    k2.a(k2.b(), false);
                }
                return false;
            }
        });
        relativeLayout.setPadding(16, 16, 16, 16);
        return relativeLayout;
    }

    public final void onBackPressed() {
        this.a(this.b(), true);
    }

    public final void onClick(View view) {
        this.a(this.b(), false);
    }

    public final void onTextChanged(CharSequence charSequence, int n2, int n3, int n4) {
    }

}

