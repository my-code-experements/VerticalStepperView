package com.liefery.android.vertical_stepper_view;

import ohos.agp.components.AttrSet;
import ohos.agp.components.BaseItemProvider;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.app.Context;

public class VerticalStepperView extends ListContainer {
    //#region constructor
    public VerticalStepperView(Context context) {
        super(context);
        initialize(context);
    }

    public VerticalStepperView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initialize(context);
    }

    public VerticalStepperView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        initialize(context);
    }
    //#endregion constructor

    private void initialize(Context context) {

        setItemClickedListener((listContainer, component, position, id) -> {
            getItemProvider().jumpTo(position);
        });
    }

    @Override
    public VerticalStepperAdapter getItemProvider() {
        return (VerticalStepperAdapter) super.getItemProvider();
    }

    @Override
    public void setItemProvider(BaseItemProvider itemProvider) {
        if (!(itemProvider instanceof VerticalStepperAdapter))
            throw new IllegalArgumentException("Must be a VerticalStepperAdapter");
        super.setItemProvider(itemProvider);
    }


    public void setStepperAdapter(VerticalStepperAdapter adapter) {
        setItemProvider(adapter);
    }
}
