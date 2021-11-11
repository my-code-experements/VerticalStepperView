package com.liefery.android.vertical_stepper_view;

import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.ElementScatter;
import ohos.agp.components.element.ShapeElement;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class VerticalStepperItemCircleView extends StackLayout {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP
            , 0x00201, "-MainAbility-");
    Text number;
    Image icon;
    int size;

    //#region constructor
    public VerticalStepperItemCircleView(Context context) {
        super(context);
        init(context);
    }

    public VerticalStepperItemCircleView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        init(context);
    }

    public VerticalStepperItemCircleView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        init(context);
    }
    //#endregion constructor


    public void init(Context context) {
        size = Util.dpToPx(getContext(), 24);
        setComponentSize(size, size);

        LayoutScatter.getInstance(context).parse(ResourceTable.Layout_vertical_stepper_view_item_circle, this, true);
        number = (Text) findComponentById(ResourceTable.Id_vertical_stepper_view_item_circle_number);
        icon = (Image) findComponentById(ResourceTable.Id_vertical_stepper_view_item_circle_icon);
    }

    public void setBackgroundActive() {

        try {
            Element shapeElement = ElementScatter.getInstance(getContext()).parse(ResourceTable.Graphic_vertical_stepper_view_item_circle_active);
            setBackground(shapeElement);

        } catch (Exception e) {
            e.printStackTrace();
            HiLog.warn(LABEL_LOG, "VerticalStepperItemCircleView: setBackgroundActive"+e);
        }
    }

    public void setBackgroundInactive() {
        try {
            Element shapeElement = ElementScatter.getInstance(getContext()).parse(ResourceTable.Graphic_vertical_stepper_view_item_circle_inactive);

            setBackground(shapeElement);
        } catch (Exception e) {
            e.printStackTrace();
            HiLog.warn(LABEL_LOG, "VerticalStepperItemCircleView: setBackgroundInactive"+e);
        }
    }

    public void setNumber(int value) {
        icon.setVisibility(HIDE);
        number.setVisibility(VISIBLE);
        number.setText(String.valueOf(value));
    }
    public void setIconCheck() {
        setIconResource( ResourceTable.Media_icon_check_white_18dp );
    }

    public void setIconEdit() {
        setIconResource( ResourceTable.Media_icon_edit_white_18dp );
    }

    public void setIconResource(int id) {
        number.setVisibility(HIDE);
        icon.setVisibility(VISIBLE);
        icon.setImageAndDecodeBounds(id);
    }
}
