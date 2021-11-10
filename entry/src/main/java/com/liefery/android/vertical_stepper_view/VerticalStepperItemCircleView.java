package com.liefery.android.vertical_stepper_view;

import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.app.Context;
import ohos.global.resource.Element;

public class VerticalStepperItemCircleView extends StackLayout {
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
            ShapeElement shapeElement = new ShapeElement();
            shapeElement.setShape(ShapeElement.OVAL);
            shapeElement.setRgbColor(new RgbColor(76, 175, 80));
            setBackground(shapeElement);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBackgroundInactive() {
        try {
            ShapeElement shapeElement = new ShapeElement();
            shapeElement.setShape(ShapeElement.OVAL);
            shapeElement.setRgbColor(new RgbColor(158, 158, 158));

            setBackground(shapeElement);
        } catch (Exception e) {
            e.printStackTrace();
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
