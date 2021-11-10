package com.liefery.android.vertical_stepper_view;

import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.agp.utils.RectFloat;
import ohos.app.Context;

class ConnectorLineDrawer {
    private final Paint paint = new Paint();

    private final RectFloat line = new RectFloat();

    ConnectorLineDrawer( Context context ) {
        int grey = context.getColor(ResourceTable.Color_vertical_stepper_view_grey_100);

        paint.setColor( new Color(grey) );
    }

    void adjust( Context context, int width, int height ) {
        line.left = Util.dpToPx( context, 19.5f );
        line.right = Util.dpToPx( context, 20.5f );
        line.top = Util.dpToPx( context, 40 );
        line.bottom = height;
    }

    void draw( Canvas canvas ) {
        canvas.drawRect( line, paint );
    }
}
