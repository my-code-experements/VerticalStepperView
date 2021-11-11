package com.liefery.android.vertical_stepper_view;

import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.agp.utils.RectFloat;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

class ConnectorLineDrawer {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP
            , 0x00201, "-MainAbility-");
    private final Paint paint = new Paint();

    private final RectFloat line = new RectFloat();
    //    static Color getColor(int index){
//        switch (index){
//            case 1:
//                return Color.RED;
//            case 2:
//                return Color.BLUE;
//            case 3:
//                return Color.CYAN;
//            case 4:
//                return Color.GREEN;
//            case 5:
//                return Color.MAGENTA;
//            case 6:
//                return Color.YELLOW;
//            default:
//            case 7:
//                return Color.DKGRAY;
//        }
//    }
    int oldHeight = -1;
    int oldWidth = -1;
    boolean shouldRedraw = true;

    ConnectorLineDrawer(Context context) {
        int grey = context.getColor(ResourceTable.Color_vertical_stepper_view_grey_100);

        paint.setColor(new Color(grey));
    }

    void adjust(Context context, int width, int height, int index) {
        if (oldHeight != height || oldWidth != width) shouldRedraw = true;
        oldHeight = height;
        oldWidth = width;

        line.left = Util.dpToPx(context, 19.5f);
        line.right = Util.dpToPx(context, 21.5f);
        line.top = Util.dpToPx(context, 40);
        line.bottom = height;
        double h = line.bottom - line.top;
//        paint.setColor(getColor(index));
//        if(h>14)paint.setColor(Color.BLACK);
//        HiLog.warn(LABEL_LOG, String.format("ConnectorLineDrawer: adjust(index: %d)(width: %d, height: %d, h: %.2f)", index, width, height, h));
    }

    void draw(Canvas canvas) {
        HiLog.warn(LABEL_LOG, "ConnectorLineDrawer: draw");
        if (shouldRedraw) {
            HiLog.warn(LABEL_LOG, "ConnectorLineDrawer: draw");
            canvas.drawRect(line, paint);
        }

    }

}
