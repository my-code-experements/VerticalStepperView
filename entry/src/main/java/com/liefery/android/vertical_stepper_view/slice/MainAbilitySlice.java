package com.liefery.android.vertical_stepper_view.slice;

import com.liefery.android.vertical_stepper_view.ResourceTable;
import com.liefery.android.vertical_stepper_view.VerticalStepperView;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbilitySlice extends AbilitySlice {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP
            , 0x00201, "-MainAbility-");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        try {

            VerticalStepperView stepper = (VerticalStepperView) findComponentById(ResourceTable.Id_stepper_list);
            stepper.setItemProvider(new MainStepperAdapter(this));
        } catch (Exception ex) {
            HiLog.warn(LABEL_LOG, "MainAbilitySlice: onStart"+ex);

            for (StackTraceElement stackTraceElement: ex.getStackTrace()){
                HiLog.warn(LABEL_LOG, ""+stackTraceElement.toString());

            }
        }

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }


}
