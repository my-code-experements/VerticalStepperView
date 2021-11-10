package com.liefery.android.vertical_stepper_view;


import ohos.agp.components.AttrHelper;
import ohos.app.Context;

class Util {
    static int dpToPx(Context context, float dp) {
        return (int) AttrHelper.vp2px(dp, context);
    }

    static int getThemeColor(Context context, int attr) {
//        TypedValue typedValue = new TypedValue();
//        TypedArray a = context.obtainStyledAttributes(
//                typedValue.data,
//                new int[]{attr});
//        int color = a.getColor(0, 0);
//        a.recycle();
        return 0;
    }
}
