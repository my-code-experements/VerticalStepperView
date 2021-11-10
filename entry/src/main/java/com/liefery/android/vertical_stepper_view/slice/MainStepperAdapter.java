package com.liefery.android.vertical_stepper_view.slice;

import com.liefery.android.vertical_stepper_view.ResourceTable;
import com.liefery.android.vertical_stepper_view.VerticalStepperAdapter;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainStepperAdapter extends VerticalStepperAdapter {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP
            , 0x00201, "-MainAbility-");

    public MainStepperAdapter( Context context ) {
        super( context );
    }


    @Override
    public CharSequence getTitle(int position) {
        return "Title: " + position;
    }

    @Override
    public CharSequence getSummary(int position) {
        return "Summary: " + position;
    }

    @Override
    public boolean isEditable(int position) {
        return position == 1;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public Component onCreateContentView(Context context, int position) {
        Component content = new MainItemView(context);
        Button actionContinue = (Button) content.findComponentById(ResourceTable.Id_action_continue);
        HiLog.warn(LABEL_LOG, "MainStepperAdapter: actionContinue: "+actionContinue);
        actionContinue.setEnabled(position < getCount() - 1);
        actionContinue.setClickedListener(component -> {
            next();
        });

        Button actionBack = (Button) content.findComponentById(ResourceTable.Id_action_back);
        actionBack.setEnabled(position > 0);
        actionBack.setClickedListener(component -> {
            previous();
        });

        return content;
    }


}
