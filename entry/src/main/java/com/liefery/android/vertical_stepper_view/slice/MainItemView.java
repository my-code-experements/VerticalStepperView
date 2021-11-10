package com.liefery.android.vertical_stepper_view.slice;

import com.liefery.android.vertical_stepper_view.ResourceTable;
import ohos.agp.components.AttrSet;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainItemView extends DirectionalLayout {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP
            , 0x00201, "-MainAbility-");

    //#region constructor
    public MainItemView(Context context) {
        super(context);
        initialize(context);
    }

    public MainItemView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initialize(context);
    }

    public MainItemView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        initialize(context);
    }
    //#endregion constructor

    private  void  initialize(Context context){
        setClipEnabled(true);
        setOrientation(VERTICAL);
        LayoutScatter.getInstance(context).parse(ResourceTable.Layout_item,this,true);
        HiLog.warn(LABEL_LOG, "MainItemView: initialize");
    }
}
